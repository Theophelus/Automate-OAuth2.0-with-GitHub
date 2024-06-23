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

                    r.put("status", status);
                    tr.setStatus(status);
                    results.add(r);

                }
            }
        }
        //return each test record
        return new TestRecord(
                totalNumberOfTestCases, passedNumberOfTestCases,
                failedNumberOfTestCases,
                duration,
                status
        );
    }

    //get the results
    public static List<Map<String, Object>> getResults() {
        return results;
    }
}
