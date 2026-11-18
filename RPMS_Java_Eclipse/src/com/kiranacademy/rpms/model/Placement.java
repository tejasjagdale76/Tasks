package com.kiranacademy.rpms.model;

public class Placement {
    private int placementId;
    private int candidateId;
    private int companyId;
    private int jobId;
    private double finalPackageLpa;
    private String joiningStatus;

    public int getPlacementId() { return placementId; }
    public void setPlacementId(int placementId) { this.placementId = placementId; }
    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }
    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }
    public double getFinalPackageLpa() { return finalPackageLpa; }
    public void setFinalPackageLpa(double finalPackageLpa) { this.finalPackageLpa = finalPackageLpa; }
    public String getJoiningStatus() { return joiningStatus; }
    public void setJoiningStatus(String joiningStatus) { this.joiningStatus = joiningStatus; }
}
