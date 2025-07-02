/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

<<<<<<< HEAD
/**
 *
 * @author ptson
 */
public class Appointment {
    private int id;
    private int doctorID;
    private String doctorName;
    private int specializationId;
    private String specializationName;
    private String description;
    private String date;
    private String startTime;
    private String endTime;
    private int roomId;
    private String roomName;
    private String status;

    public Appointment(int id, String doctorName, String specializationName, String description, String date, String startTime, String endTime, String roomName, String status) {
        this.id = id;
        this.doctorName = doctorName;
        this.specializationName = specializationName;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.status = status;
    }
    
    
=======
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
>>>>>>> an

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
=======
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
>>>>>>> an
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

<<<<<<< HEAD
    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
=======
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
>>>>>>> an
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

<<<<<<< HEAD
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
=======
    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

>>>>>>> an
    
}
