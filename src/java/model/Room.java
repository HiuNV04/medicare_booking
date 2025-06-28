package model;

public class Room {
    private int id;
    private String name;
    private int doctorId;

    public Room() {
    }

    public Room(int id, String name, int doctorId) {
        this.id = id;
        this.name = name;
        this.doctorId = doctorId;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
} 