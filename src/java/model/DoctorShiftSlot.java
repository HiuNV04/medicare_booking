/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ptson
 */
public class DoctorShiftSlot {
    private int id;
    private int doctocId;
    private String slotStartTime;
    private String slotEndTime;
    private String date;
    private boolean isBooked;

    public DoctorShiftSlot(int doctocId, String slotStartTime, String slotEndTime, String date, boolean isBooked) {
        this.doctocId = doctocId;
        this.slotStartTime = slotStartTime;
        this.slotEndTime = slotEndTime;
        this.date = date;
        this.isBooked = isBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctocId() {
        return doctocId;
    }

    public void setDoctocId(int doctocId) {
        this.doctocId = doctocId;
    }

    public String getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(String slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public String getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(String slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

       
}
