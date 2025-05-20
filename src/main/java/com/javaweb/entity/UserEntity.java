package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    // liên kết nhieu nhieu: 1 nhân viên quản lý nhiều tòa nhà và 1 tòa nhà đc quản lý bỏi 1 hoặc nhiêu nhân viên
    @ManyToMany(mappedBy = "staffsAssBuilding", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildingsAssByStaff = new ArrayList<>();

    //lk n-n 1 toa nha nhieu ng quan ly va nhieu toa nha dc 1 ng quan ly
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
//    List<AssignmentBuildingEntity> staffassbuild = new ArrayList<>();
//
//    public List<AssignmentBuildingEntity> getStaffassbuild() {
//        return staffassbuild;
//    }
//
//    public void setStaffassbuild(List<AssignmentBuildingEntity> staffassbuild) {
//        this.staffassbuild = staffassbuild;
//    }

    public List<BuildingEntity> getBuildingsAssByStaff() {
        return buildingsAssByStaff;
    }

    public void setBuildingsAssByStaff(List<BuildingEntity> buildingsAssByStaff) {
        this.buildingsAssByStaff = buildingsAssByStaff;
    }


// getter and setter

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
