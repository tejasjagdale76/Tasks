package com.kiranacademy.rpms.model;

public class Offer {
    private int offerId;
    private String offerCode;
    private int applicationId;
    private String designation;
    private double offeredPackageLpa;
    private String offerStatus;

    public int getOfferId() { return offerId; }
    public void setOfferId(int offerId) { this.offerId = offerId; }
    public String getOfferCode() { return offerCode; }
    public void setOfferCode(String offerCode) { this.offerCode = offerCode; }
    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public double getOfferedPackageLpa() { return offeredPackageLpa; }
    public void setOfferedPackageLpa(double offeredPackageLpa) { this.offeredPackageLpa = offeredPackageLpa; }
    public String getOfferStatus() { return offerStatus; }
    public void setOfferStatus(String offerStatus) { this.offerStatus = offerStatus; }
}
