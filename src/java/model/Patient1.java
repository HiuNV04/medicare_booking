/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Patient1 {
    
    private int id;
    private String role;
    private String email;
    private String username;
    private String password;
    private String fullname, gender, dob, identity, insurance, phone, address;
    private String img;

    public Patient1() {
    }
    
    public Patient1(String username) {
        this.username = username;
    }

    //cái này để update 1 thông tin bệnh nhân
    public Patient1(String dob, String gender, String identity, String insurance, String phone, String address, String img) {
        this.gender = gender;
        this.dob = dob;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.img = img;
    }

    //constructor này để lấy danh sách bệnh nhân bên lễ tân
    public Patient1(int id, String fullname, String gender, String identity, String insurance, String phone, String address, String img) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.img = img;
    }

    //constructor này để lấy 1 bệnh nhân bên lễ tân ra update
    public Patient1(int id, String fullname, String gender, String dob, String identity, String insurance, String phone, String address, String img) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.dob = dob;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.img = img;
    }

    //constructor này để thêm mới 1 bệnh nhân bên lễ tân
    public Patient1(String role, String fullname, String dob, String gender, String identity, String insurance, String phone, String address) {
        this.role = role;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
    }

    public Patient1(int id, String email, String username, String password, String fullname, String gender, String dob, String identity, String insurance, String phone, String address, String img) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.dob = dob;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + "fullname=" + fullname + ", gender=" + gender + ", dob=" + dob + ", identity=" + identity + ", insurance=" + insurance + ", phone=" + phone + ", address=" + address + ", img=" + img + '}';
    }
}
