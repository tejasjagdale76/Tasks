package com.kiranacademy.rpms.model;

import java.time.LocalDate;

public class Job {
    private int jobId;
    private String jobCode;
    private int companyId;
    private String jobTitle;
    private String location;
    private int openings;
    private double minPackageLpa;
    private double maxPackageLpa;
    private double minPercentage;
    private int minPassingYear;
    private int maxPassingYear;
    private int maxBacklogs;
    private LocalDate applicationDeadline;
    private String status;

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }
    public String getJobCode() { return jobCode; }
    public void setJobCode(String jobCode) { this.jobCode = jobCode; }
    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getOpenings() { return openings; }
    public void setOpenings(int openings) { this.openings = openings; }
    public double getMinPackageLpa() { return minPackageLpa; }
    public void setMinPackageLpa(double minPackageLpa) { this.minPackageLpa = minPackageLpa; }
    public double getMaxPackageLpa() { return maxPackageLpa; }
    public void setMaxPackageLpa(double maxPackageLpa) { this.maxPackageLpa = maxPackageLpa; }
    public double getMinPercentage() { return minPercentage; }
    public void setMinPercentage(double minPercentage) { this.minPercentage = minPercentage; }
    public int getMinPassingYear() { return minPassingYear; }
    public void setMinPassingYear(int minPassingYear) { this.minPassingYear = minPassingYear; }
    public int getMaxPassingYear() { return maxPassingYear; }
    public void setMaxPassingYear(int maxPassingYear) { this.maxPassingYear = maxPassingYear; }
    public int getMaxBacklogs() { return maxBacklogs; }
    public void setMaxBacklogs(int maxBacklogs) { this.maxBacklogs = maxBacklogs; }
    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(LocalDate applicationDeadline) { this.applicationDeadline = applicationDeadline; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return jobId + " | " + jobCode + " | " + jobTitle + " | " + location + " | " + status;
    }
}
