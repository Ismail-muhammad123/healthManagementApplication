package com.example.hospitalmanagement;


import android.os.Parcel;
import android.os.Parcelable;

public class PatientRVModal implements Parcelable {

    private String patFullname;
    private String patPhoneNumber;
    private String patAddress;
    private String patEmail;
    private String patDetails;
    private String availabilityPat;
    private String departmentPat;
    private String patientId;

    public PatientRVModal() {
    }

    public PatientRVModal(String patFullname, String patPhoneNumber, String patAddress, String patEmail, String patDetails, String availabilityPat, String departmentPat, String patientId) {
        this.patFullname = patFullname;
        this.patPhoneNumber = patPhoneNumber;
        this.patAddress = patAddress;
        this.patEmail = patEmail;
        this.patDetails = patDetails;
        this.availabilityPat = availabilityPat;
        this.departmentPat = departmentPat;
        this.patientId = patientId;
    }

    protected PatientRVModal(Parcel in) {
        patFullname = in.readString();
        patPhoneNumber = in.readString();
        patAddress = in.readString();
        patEmail = in.readString();
        patDetails = in.readString();
        availabilityPat = in.readString();
        departmentPat = in.readString();
        patientId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patFullname);
        dest.writeString(patPhoneNumber);
        dest.writeString(patAddress);
        dest.writeString(patEmail);
        dest.writeString(patDetails);
        dest.writeString(availabilityPat);
        dest.writeString(departmentPat);
        dest.writeString(patientId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientRVModal> CREATOR = new Creator<PatientRVModal>() {
        @Override
        public PatientRVModal createFromParcel(Parcel in) {
            return new PatientRVModal(in);
        }

        @Override
        public PatientRVModal[] newArray(int size) {
            return new PatientRVModal[size];
        }
    };

    public String getPatFullname() {
        return patFullname;
    }

    public void setPatFullname(String patFullname) {
        this.patFullname = patFullname;
    }

    public String getPatPhoneNumber() {
        return patPhoneNumber;
    }

    public void setPatPhoneNumber(String patPhoneNumber) {
        this.patPhoneNumber = patPhoneNumber;
    }

    public String getPatAddress() {
        return patAddress;
    }

    public void setPatAddress(String patAddress) {
        this.patAddress = patAddress;
    }

    public String getPatEmail() {
        return patEmail;
    }

    public void setPatEmail(String patEmail) {
        this.patEmail = patEmail;
    }

    public String getPatDetails() {
        return patDetails;
    }

    public void setPatDetails(String patDetails) {
        this.patDetails = patDetails;
    }

    public String getAvailabilityPat() {
        return availabilityPat;
    }

    public void setAvailabilityPat(String availabilityPat) {
        this.availabilityPat = availabilityPat;
    }

    public String getDepartmentPat() {
        return departmentPat;
    }

    public void setDepartmentPat(String departmentPat) {
        this.departmentPat = departmentPat;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
