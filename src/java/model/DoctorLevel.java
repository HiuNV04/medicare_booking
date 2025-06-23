package model;

public class DoctorLevel {
    private int id;
    private String name;
    private float examinationFee;

    public DoctorLevel() {}

    public DoctorLevel(int id, String name, float examinationFee) {
        this.id = id;
        this.name = name;
        this.examinationFee = examinationFee;
    }

    // Getter/Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getExaminationFee() { return examinationFee; }
    public void setExaminationFee(float examinationFee) { this.examinationFee = examinationFee; }
}
