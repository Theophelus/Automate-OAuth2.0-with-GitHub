package org.anele.pojo;

public class TestRecord {
    private int totalNumberOfTestCases=0;
    private int passedNumberOfTestCases=0;
    private int failedNumberOfTestCases=0;
    private String duration;
    private String status;

    public TestRecord(){
    }

    public TestRecord(int totalNumberOfTestCases, int passedNumberOfTestCases, int failedNumberOfTestCases, String duration, String status) {
        this.totalNumberOfTestCases = totalNumberOfTestCases;
        this.passedNumberOfTestCases = passedNumberOfTestCases;
        this.failedNumberOfTestCases = failedNumberOfTestCases;
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
