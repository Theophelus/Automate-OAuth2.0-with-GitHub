package org.anele.utils;

import org.anele.pojo.TestRecord;
import org.testng.*;

import java.util.*;

import static org.anele.utils.ExecutionTimesUtils.calculateTestDurations;

public class BuilderResultsUtils {

    private static List<Map<String, Object>> results;
    static String status = null;

    public static TestRecord buildRecord(List<ISuite> suites) {
        int totalNumberOfTestCases = 0;
        int passedNumberOfTestCases = 0;
        int failedNumberOfTestCases = 0;
        int skippedNumberOfTestCases = 0;

        String duration = null;

        //define TestRecord object to store data
        TestRecord tr = new TestRecord();
        results = new ArrayList<>();
        //loop through the suites
        for (ISuite suite : suites) {
            //get results from the suite and store into a map
            Map<String, ISuiteResult> suiteResult = suite.getResults();

            for (ISuiteResult result : suiteResult.values()) {
                ITestContext tc = result.getTestContext();

                totalNumberOfTestCases += tc.getAllTestMethods().length;
                passedNumberOfTestCases += tc.getPassedTests().size();
                failedNumberOfTestCases += tc.getFailedTests().size();
                skippedNumberOfTestCases += tc.getSkippedTests().size();

                Set<ITestResult> it = new HashSet<>();
                it.addAll(tc.getPassedTests().getAllResults());
                it.addAll(tc.getFailedTests().getAllResults());
                it.addAll(tc.getSkippedTests().getAllResults());
                //get all test methods
                ITestNGMethod[] allTestMethods = tc.getAllTestMethods();
                for (ITestNGMethod iTestNGMethod : allTestMethods) {
                    duration = String.valueOf(calculateTestDurations(it, iTestNGMethod.getMethodName()));
                    Map<String, Object> r = new HashMap<>();
                    r.put("TestSuite", suite.getName());
                    r.put("TestCase", iTestNGMethod.getMethodName());
                    r.put("Duration", duration);
                    //extract status for each test record
                    testCaseStatus(iTestNGMethod, tc);
                    //push status code, to be rendered
                    r.put("status", status);
                    results.add(r);
                }

            }
        }
        //return each test record
        return new TestRecord(
                totalNumberOfTestCases, passedNumberOfTestCases,
                failedNumberOfTestCases, skippedNumberOfTestCases,
                duration,
                status
        );
    }

    private static void testCaseStatus(ITestNGMethod iTestNGMethod, ITestContext tc) {
        // Check if the test method is in the list of passed tests
        boolean indicator = false;
        for (ITestNGMethod method : tc.getPassedTests().getAllMethods()) {
            if (method.getMethodName().contains(iTestNGMethod.getMethodName())) {
                status = "pass";
                indicator = true;
                break;
            }
        }
        // If the test is not passed, flag as failed otherwise skip
        if (!indicator) {
            for (ITestNGMethod method : tc.getFailedTests().getAllMethods()) {
                if (method.getMethodName().contains(iTestNGMethod.getMethodName())) {
                    status = "fail";
                    break;
                }
            }
        }
        for (ITestNGMethod method : tc.getSkippedTests().getAllMethods()) {
            if (method.getMethodName().contains(iTestNGMethod.getMethodName())) {
                status = "skip";
                break;
            }
        }
    }

    //get the results
    public static List<Map<String, Object>> getResults() {
        return results;
    }
}
