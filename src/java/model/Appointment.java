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

    private String note;
    private String roomName;
    private String specialization;
    private String doctorLevel;
    private Date patientDob;
    private String patientGender;


    public Appointment() {
    }

    public Appointment(int id, int patientId, String patientName, int doctorId, String doctorName, Date date, Time slotStartTime, Time slotEndTime, int roomId, String confirmationStatus, String note, String roomName, String specialization, String doctorLevel, Date patientDob, String patientGender) {
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
        this.note = note;
        this.roomName = roomName;
        this.specialization = specialization;
        this.doctorLevel = doctorLevel;
        this.patientDob = patientDob;
        this.patientGender = patientGender;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDoctorLevel() {
        return doctorLevel;
    }

    public void setDoctorLevel(String doctorLevel) {
        this.doctorLevel = doctorLevel;
    }

    public Date getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(Date patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }


    
}
