/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jackd
 */
public class DoctorShiftSlot {
    private int id;
    private int doctorId;
    private Time slotStartTime;
    private Time slotEndTime;
    private Date date;
    private boolean isBooked;

    public DoctorShiftSlot() {
    }

    public DoctorShiftSlot(int id, int doctorId, Time slotStartTime, Time slotEndTime, Date date, boolean isBooked) {
        this.id = id;
        this.doctorId = doctorId;
        this.slotStartTime = slotStartTime;
        this.slotEndTime = slotEndTime;
        this.date = date;
        this.isBooked = isBooked;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Time getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(Time slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public Time getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(Time slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
