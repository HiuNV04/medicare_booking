/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ADDMIN
 */
public class Appointment {
    private int id;
    private int patientId;
    private String patientName;

    private int doctorId;
    private String doctorName;

    private Date date;
    private Time slotStartTime;
    private Time slotEndTime;

    private int roomId;
    private String confirmationStatus;

    public Appointment() {
    }

    public Appointment(int id, int patientId, String patientName, int doctorId, String doctorName, Date date, Time slotStartTime, Time slotEndTime, int roomId, String confirmationStatus) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.date = date;
        this.slotStartTime = slotStartTime;
        this.slotEndTime = slotEndTime;
        this.roomId = roomId;
        this.confirmationStatus = confirmationStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    
}
