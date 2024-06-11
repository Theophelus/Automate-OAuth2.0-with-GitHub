package org.anele.pojo;

public class Result {
    private int totalNumberOfTestCases;
    private int passedNumberOfTestCases;
    private int failedNumberOfTestCases;
    private String suiteName;
    private String tcName;
    private StringBuilder totalTimeExecution;
    private String duration;
    private String status;

    public Result(){
    }

    public Result(int totalNumberOfTestCases, int passedNumberOfTestCases, int failedNumberOfTestCases, String suiteName, String tcName, StringBuilder totalTimeExecution, String duration, String status) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
        this.passedNumberOfTestCases = passedNumberOfTestCases;
        this.failedNumberOfTestCases = failedNumberOfTestCases;
        this.suiteName = suiteName;
        this.tcName = tcName;
        this.totalTimeExecution = totalTimeExecution;
        this.duration = duration;
        this.status = status;
    }

    public int getTotalNumberOfTestCases() {
        return totalNumberOfTestCases;
    }

    public void setTotalNumberOfTestCases(int totalNumberOfTestCases) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
    }

    public int getPassedNumberOfTestCases() {
        return passedNumberOfTestCases;
    }

    public void setPassedNumberOfTestCases(int passedNumberOfTestCases) {
        this.passedNumberOfTestCases = passedNumberOfTestCases;
    }

    public int getFailedNumberOfTestCases() {
        return failedNumberOfTestCases;
    }

    public void setFailedNumberOfTestCases(int failedNumberOfTestCases) {
        this.failedNumberOfTestCases = failedNumberOfTestCases;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    public StringBuilder getTotalTimeExecution() {
        return totalTimeExecution;
    }

    public void setTotalTimeExecution(StringBuilder totalTimeExecution) {
        this.totalTimeExecution = totalTimeExecution;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
