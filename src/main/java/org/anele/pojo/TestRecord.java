package org.anele.pojo;

public class TestRecord {
    private int totalNumberOfTestCases=0;
    private int passedNumberOfTestCases=0;
    private int failedNumberOfTestCases=0;
    private int skippedNumberOfTestCases=0;
    private String duration;
    private String status;

    public TestRecord(){
    }

    public TestRecord(int totalNumberOfTestCases, int passedNumberOfTestCases, int failedNumberOfTestCases, int skippedNumberOfTestCases, String duration, String status) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
        this.passedNumberOfTestCases = passedNumberOfTestCases;
        this.failedNumberOfTestCases = failedNumberOfTestCases;
        this.skippedNumberOfTestCases = skippedNumberOfTestCases;
        this.duration = duration;
        this.status = status;
    }

    public int getTotalNumberOfTestCases() {
        return totalNumberOfTestCases;
    }

    public int getPassedNumberOfTestCases() {
        return passedNumberOfTestCases;
    }

    public int getFailedNumberOfTestCases() {
        return failedNumberOfTestCases;
    }

    public int getSkippedNumberOfTestCases() {
        return skippedNumberOfTestCases;
    }
}
