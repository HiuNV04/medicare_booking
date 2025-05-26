package model;

public class Staff {
    private int id;
    private int accountId;
    private String imageURL;
    private String address;
    private java.sql.Date dateOfBirth;
    private String fullName;
    private String gender;
    private String phoneNumber;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public java.sql.Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(java.sql.Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
} 