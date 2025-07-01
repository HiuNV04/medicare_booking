/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Receptionist extends Staff {

    public Receptionist() {
        super();
    }

    public Receptionist(Staff s) {
        this.setId(s.getId());
        this.setFullName(s.getFullName());
        this.setUsername(s.getUsername());
        this.setPassword(s.getPassword());
        this.setRole(s.getRole());
        this.setEmail(s.getEmail());
        this.setGender(s.getGender());
        this.setDateOfBirth(s.getDateOfBirth());
        this.setPhoneNumber(s.getPhoneNumber());
        this.setAddress(s.getAddress());
        this.setStatus(s.isStatus());
        this.setImageUrl(s.getImageUrl());
    }
}
