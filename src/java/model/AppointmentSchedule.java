package model;

public class AppointmentSchedule {
    private int id;
    private int patientId;
    private int doctorShiftId;
    private int roomId;
    private String confirmationStatus;

    public AppointmentSchedule() {
    }

    public AppointmentSchedule(int id, int patientId, int doctorShiftId, int roomId, String confirmationStatus) {
        this.id = id;
        this.patientId = patientId;
        this.doctorShiftId = doctorShiftId;
        this.roomId = roomId;
        this.confirmationStatus = confirmationStatus;
    }

    // Getters and Setters
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

    public int getDoctorShiftId() {
        return doctorShiftId;
    }

    public void setDoctorShiftId(int doctorShiftId) {
        this.doctorShiftId = doctorShiftId;
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