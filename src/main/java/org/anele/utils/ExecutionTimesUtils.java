package org.anele.utils;

import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class ExecutionTimesUtils {

    //define a variable to store minutes
    private static String minutes;
    static double totalDurationMinutes = 0;

    //calculate Duration of each method
    public static long calculateTestDurations(Set<ITestResult> testResults, String method) {
        //duration of each method
        long duration = 0;
        long totalDurationMillis = 0;
        for (ITestResult result : testResults) {
            //get method name
            var methodName = result.getMethod().getMethodName();
            if (methodName.equals(method)) {
                //calculate duration
                totalDurationMillis = result.getEndMillis() - result.getStartMillis();
                duration += totalDurationMillis;
                //break after each method
                totalDurationMinutes += (((double) duration / (1000 * 60)) % 60);
                 minutes = String.format("%,.2f min", totalDurationMinutes);
                break;
            }
        }

        return duration;
    }
    //get current date
    public static String getCurrentDay() {
        //Get current date
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
    //get minutes values
    public static String getMinutes() {
        return minutes;
    }
}
