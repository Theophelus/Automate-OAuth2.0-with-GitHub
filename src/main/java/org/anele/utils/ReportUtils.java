package org.anele.utils;

import org.anele.helpers.HandlebarsTemplateHelper;
import org.anele.helpers.LogHelper;
import org.anele.pojo.TestRecord;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.*;

import static org.anele.utils.BuilderResultsUtils.buildRecord;
import static org.anele.utils.BuilderResultsUtils.getResults;
import static org.anele.utils.ExecutionTimesUtils.*;
import static org.anele.utils.QueryableUtils.getBuildRequests;

public class ReportUtils implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        var result = buildRecord(suites);
        try {
            // Prepare data for the main template
            Map<String, Object> headData = new HashMap<>();
            headData.put("currentDate", getCurrentDay());
            var statisticsData = getStatisticsData(result);
            var executionInfoData = getExecutionInfoData(result);
            var tableData = getTableData();

            //load templates
            var head = HandlebarsTemplateHelper.renderPartials(
                    "src/main/resources/templates/partials/heads.hbs", headData);
            var statistics = HandlebarsTemplateHelper.renderPartials(
                    "src/main/resources/templates/partials/statistics.hbs", statisticsData);
            var executionInfo = HandlebarsTemplateHelper.renderPartials(
                    "src/main/resources/templates/partials/executionInfo.hbs", executionInfoData);
            var tables = HandlebarsTemplateHelper.renderMainTemplates(
                    "src/main/resources/templates/partials/tables.hbs", tableData);

            //combine the templates
            Map<String, Object> data = new HashMap<>();
            data.put("heads", head);
            data.put("statistics", statistics);
            data.put("executionInfo", executionInfo);
            data.put("tables", tables);

            //render main template
            String mainHtmlContent = HandlebarsTemplateHelper.renderMainTemplates("src/main/resources/templates/layouts/main.hbs", data);

        } catch (Exception e) {
            LogHelper.error("Error occurred with creating report:", e.getMessage());
        }
    }

    private static Map<String, Object> getTableData() {
        // Prepare data for the table template
        Map<String, Object> tableData = new HashMap<>();
        //get test results to populate the table
        tableData.put("table", getResults());
        var buildRequests = getBuildRequests();
        System.out.println("Build Request: " + buildRequests);

        tableData.putAll(buildRequests);
        return tableData;
    }

    private static Map<String, Object> getExecutionInfoData(TestRecord result) {
        // Prepare data for the execution info template
        Map<String, Object> executionInfoData = new HashMap<>();
        executionInfoData.put("PassTestCaseExecution",
                Math.round(((double) result.getPassedNumberOfTestCases() / result.getTotalNumberOfTestCases()) * 100));
        executionInfoData.put("FailedTestCasesExecuted",
                Math.round(((double) result.getFailedNumberOfTestCases() / result.getTotalNumberOfTestCases()) * 100));
        executionInfoData.put("skippedTests",
                Math.round(((double) result.getSkippedNumberOfTestCases() / result.getTotalNumberOfTestCases()) * 100));
        executionInfoData.put("TotalTimeExecution", getMinutes());
        return executionInfoData;
    }

    private static Map<String, Object> getStatisticsData(TestRecord result) {
        // Prepare data for the statistics template
        Map<String, Object> statisticsData = new HashMap<>();
        statisticsData.put("executedTestCases", result.getTotalNumberOfTestCases()); // You need to define getStatistics() method to provide statistics data
        statisticsData.put("passedTestCases", result.getPassedNumberOfTestCases());
        statisticsData.put("failedTestCases", result.getFailedNumberOfTestCases());
        statisticsData.put("skippedTestCases", result.getSkippedNumberOfTestCases());

        return statisticsData;

    }
}
