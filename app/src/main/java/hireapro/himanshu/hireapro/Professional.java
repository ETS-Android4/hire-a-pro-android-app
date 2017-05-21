package hireapro.himanshu.hireapro;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by root on 5/18/17.
 */

public class Professional implements Serializable {

    private String proID,name,emailID,job,address,profilePictureURL;
    private long phoneNumber,secondryPhoneNumber;
    private int profilesPictureID;
    private float baseRate,locationLatitude,locationLongitude,distanceFromUser;
    private Bitmap userImage;

    public Professional() {
    }

    public float getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(float distanceFromUser) {

        this.distanceFromUser = (float) (Math.round(distanceFromUser * 100.0) / 100.0);
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getSecondryPhoneNumber() {
        return secondryPhoneNumber;
    }

    public void setSecondryPhoneNumber(long secondryPhoneNumber) {
        this.secondryPhoneNumber = secondryPhoneNumber;
    }

    public int getProfilesPictureID() {
        return profilesPictureID;
    }

    public void setProfilesPictureID(int profilesPictureID) {
        this.profilesPictureID = profilesPictureID;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public float getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(float baseRate) {
        this.baseRate = baseRate;
    }

    public float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }
}
