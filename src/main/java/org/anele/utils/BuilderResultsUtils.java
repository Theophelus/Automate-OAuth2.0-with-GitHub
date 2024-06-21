package org.anele.utils;

import org.anele.pojo.TestRecord;
import org.testng.*;

import java.util.*;

import static org.anele.utils.ExecutionTimesUtils.calculateTestDurations;

public class BuilderResultsUtils {

    private static List<Map<String, Object>> results;
    public static TestRecord buildRecord(List<ISuite> suites){
        //define TestRecord object to store data
        TestRecord tr = new TestRecord();
        results = new ArrayList<>();

        //loop through the list of iSuites
        for(ISuite suite: suites){

            //get results from the suite and store into a map
            Map<String, ISuiteResult> suiteResult = suite.getResults();
            for(ISuiteResult result: suiteResult.values()){

                ITestContext tc = result.getTestContext();
                //save test results into the Test Records class to count each tests statuses
                tr.setTotalNumberOfTestCases((tc.getAllTestMethods().length) +1);
                tr.setPassedNumberOfTestCases((tc.getPassedTests().size())+1);
                tr.setFailedNumberOfTestCases((tc.getFailedTests().size()) +1);

                Set<ITestResult> it = new HashSet<>();
                it.addAll(tc.getPassedTests().getAllResults());
                it.addAll(tc.getFailedTests().getAllResults());
                //get all test methods
                ITestNGMethod[] allTestMethods = tc.getAllTestMethods();
                for (ITestNGMethod iTestNGMethod : allTestMethods) {
                    //calculate duration
                    tr.setDuration(String.valueOf(calculateTestDurations(it, iTestNGMethod.getMethodName())));
                    // add a map to append data into the template
                    Map<String, Object> r = new HashMap<>();
                    r.put("TestSuite", suite.getName());
                    r.put("TestCase", iTestNGMethod.getMethodName());
                    r.put("Duration", tr.getDuration());

                    // add result into the list
                    results.add(r);
                }

            }
        }


        return tr;
    }

    private static void testStatus(ITestNGMethod iTestNGMethod, ITestContext tc, Map<String, Object> r, TestRecord tr) {

    }

    //get results
    public static List<Map<String, Object>> getResults() {
        return results;
    }
}
