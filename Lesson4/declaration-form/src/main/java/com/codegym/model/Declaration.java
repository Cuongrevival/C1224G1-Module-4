package com.codegym.model;


import java.util.List;

public class Declaration {
    private String name;
    private String birth;
    private String gender;
    private String nationality;
    private String id;
    private String vehicle;
    private String vehicleId;
    private String startDate;
    private String endDate;
    private String visitedPlace;
    private String province;
    private String district;
    private String commune;
    private String address;
    private String phone;
    private String email;
    private List<String> symptomList;
    private List<String> exposureHistoryList;

    public Declaration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVisitedPlace() {
        return visitedPlace;
    }

    public void setVisitedPlace(String visitedPlace) {
        this.visitedPlace = visitedPlace;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(List<String> symptomList) {
        this.symptomList = symptomList;
    }

    public List<String> getExposureHistoryList() {
        return exposureHistoryList;
    }

    public void setExposureHistoryList(List<String> exposureHistoryList) {
        this.exposureHistoryList = exposureHistoryList;
    }
}