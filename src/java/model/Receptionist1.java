/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Receptionist1 {
      private int id;
    private String username, role, email, password, fullname, gender, dob, phone, address, img;
    
    public Receptionist1(String email) {
        this.email = email;
    }

    public Receptionist1(String fullname, String gender, String phone, String address, String img) {
        this.fullname = fullname;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.img = img;
    } 
            
    public Receptionist1(int id, String role, String email, String username, String password, String fullname, String dob, String gender, String phone, String address, String img) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.dob = dob;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Receptionist{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", fullname=" + fullname + ", gender=" + gender + ", dob=" + dob + ", phone=" + phone + ", address=" + address + ", img=" + img + '}';
    }
}
