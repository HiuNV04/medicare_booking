
package model;


public class Patient {

    private int id;
    private String username;
    private String email;
    private String password;
    private String fullname, dob, gender, identity, insurance, phone, address, job;
    private String img;
    
    public Patient(int id, String fullname, String gender, String identity, String insurance, String phone, String address, String img) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.img = img;
    }

    public Patient(String fullname, String dob, String gender, String identity, String insurance, String phone, String address, String job, String img) {
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.job = job;
        this.img = img;
    }

    public Patient(int id, String username, String email, String password, String fullname, String dob, String gender, String identity, String insurance, String phone, String address, String job, String img) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.identity = identity;
        this.insurance = insurance;
        this.phone = phone;
        this.address = address;
        this.job = job;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String toString1() {
        return "Patient{" + "id=" + id + ", fullname=" + fullname + ", gender=" + gender + ", identity=" + identity + ", insurance=" + insurance + ", phone=" + phone + ", address=" + address + ", img=" + img + '}';
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", fullname=" + fullname + ", dob=" + dob + ", gender=" + gender + ", identity=" + identity + ", insurance=" + insurance + ", phone=" + phone + ", address=" + address + ", job=" + job + ", img=" + img + '}';
    }
}
