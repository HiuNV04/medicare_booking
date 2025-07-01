/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model_doctor;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class Slot {

    private int slotId;
    private int doctorId;
    private int specializationId;
    private int levelId;

    private Time start;
    private Time end;
    private Date slotDate;
    private boolean booked;

    // Thêm cho hiển thị
    private String doctorName;
    private String specializationName;
    private String levelName;

    public Slot() {
    }

    public Slot(int slotId, int doctorId, int specializationId, int levelId, Time start, Time end, Date slotDate, boolean booked, String doctorName, String specializationName, String levelName) {
        this.slotId = slotId;
        this.doctorId = doctorId;
        this.specializationId = specializationId;
        this.levelId = levelId;
        this.start = start;
        this.end = end;
        this.slotDate = slotDate;
        this.booked = booked;
        this.doctorName = doctorName;
        this.specializationName = specializationName;
        this.levelName = levelName;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    

    
    

}
