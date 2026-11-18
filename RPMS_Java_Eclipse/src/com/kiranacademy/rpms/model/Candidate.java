package com.kiranacademy.rpms.model;

public class Candidate {
    private int candidateId;
    private String candidateCode;
    private String fullName;
    private String email;
    private String mobile;
    private String qualification;
    private int passingYear;
    private double percentage;
    private int backlogCount;
    private String primaryTechnology;
    private String profileStatus;
    private String placementStatus;

    public Candidate() {}

    public Candidate(String candidateCode, String fullName, String email, String mobile,
                     String qualification, int passingYear, double percentage,
                     int backlogCount, String primaryTechnology) {
        this.candidateCode = candidateCode;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.qualification = qualification;
        this.passingYear = passingYear;
        this.percentage = percentage;
        this.backlogCount = backlogCount;
        this.primaryTechnology = primaryTechnology;
        this.profileStatus = "ACTIVE";
        this.placementStatus = "NOT_PLACED";
    }

    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }

    public String getCandidateCode() { return candidateCode; }
    public void setCandidateCode(String candidateCode) { this.candidateCode = candidateCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public int getPassingYear() { return passingYear; }
    public void setPassingYear(int passingYear) { this.passingYear = passingYear; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public int getBacklogCount() { return backlogCount; }
    public void setBacklogCount(int backlogCount) { this.backlogCount = backlogCount; }

    public String getPrimaryTechnology() { return primaryTechnology; }
    public void setPrimaryTechnology(String primaryTechnology) { this.primaryTechnology = primaryTechnology; }

    public String getProfileStatus() { return profileStatus; }
    public void setProfileStatus(String profileStatus) { this.profileStatus = profileStatus; }

    public String getPlacementStatus() { return placementStatus; }
    public void setPlacementStatus(String placementStatus) { this.placementStatus = placementStatus; }

    @Override
    public String toString() {
        return candidateId + " | " + candidateCode + " | " + fullName + " | " +
               primaryTechnology + " | " + profileStatus + " | " + placementStatus;
    }
}
