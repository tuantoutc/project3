package com.javaweb.entity;

import com.javaweb.enums.District;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name="name")
    private String name;

    @Column(name="numberofbasement")
    private Long numberOfBasement;

    @Column(name="ward")
    private String ward;

    @Column(name="structure")
    private String structure;

    @Column(name="street")
    private String street;

    @Column(name="managername")
    private String managerName;

    @Column(name="managerphone")
    private String managerPhone;

    @Column(name="direction")
    private String direction;// hướng

    @Column(name="floorarea")
    private Long floorArea; // dien tich sàn

    @Column(name="rentprice")
    private Long rentPrice;// gia thuê

    @Column(name="servicefee")
    private String serviceFee;// phí dịch vụ

    @Column(name="brokeragefee")
    private String brokerageFee ;// phí môi giới

    @Column(name="electricityfee")
    private String electricityFee ;

    @Column(name="deposit")
    private String deposit ;

    @Column(name="level")
    private String level;// hạng nhà

    @Column(name ="district")
    private String district;

    @Column(name="type")
    private String typeCode ;

    @Column(name="avatar")
    private String image ;

    @Column(name="payment")
    private String payment;

    @Column(name="renttime")
    private String rentTime;

    @Column(name="decorationtime")
    private String decorationTime;

    @Column(name="rentpricedescription")
    private String rentPriceDescription;

    @Column(name="carfee")
    private String carFee;

    @Column(name="motofee")
    private String motoFee;

    @Column(name="overtimefee")
    private String overtimeFee;

    @Column(name="note")
    private String note;

// lk n-n 1 building dc quản lý boi 1 hoac nhieu ng
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
//    List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "assignmentbuilding",
//            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false)
//    )
//    List<UserEntity> users = new ArrayList<>();

//    //lk n-n 1 toa nha nhieu ng quan ly va nhieu toa nha dc 1 ng quan ly
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
//    List<AssignmentBuildingEntity> buildassstaff = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    private List<RentAreaEntity> rentareas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
    joinColumns = @JoinColumn(name = "buildingid", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffsAssBuilding = new ArrayList<>();




    //getter va setter


    public List<UserEntity> getStaffsAssBuilding() { return staffsAssBuilding; }

    public void setStaffsAssBuilding(List<UserEntity> staffsAssBuilding) {   this.staffsAssBuilding = staffsAssBuilding;  }

    public String getNote() { return note; }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public void setMotoFee(String motoFee) {
        this.motoFee = motoFee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public List<RentAreaEntity> getRentareas() {
        return rentareas;
    }

    public void setRentareas(List<RentAreaEntity> rentareas) {
        this.rentareas = rentareas;
    }
}