/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class DoctorLevel {
    private int id;
    private String name;
    private float examinationFee;

    public DoctorLevel(int id, String name, float examinationFee) {
        this.id = id;
        this.name = name;
        this.examinationFee = examinationFee;
    }

    public DoctorLevel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getExaminationFee() {
        return examinationFee;
    }

    public void setExaminationFee(float examinationFee) {
        this.examinationFee = examinationFee;
    }

}
