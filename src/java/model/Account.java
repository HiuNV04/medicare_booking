/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Account {
    private int id;
    private String email;
    private String username;
    private String password;
    private String passwordHash;
    private int roleId;
    private String resetToken;
    private java.sql.Timestamp resetTokenExpiry;
    private boolean status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }
    public java.sql.Timestamp getResetTokenExpiry() { return resetTokenExpiry; }
    public void setResetTokenExpiry(java.sql.Timestamp resetTokenExpiry) { this.resetTokenExpiry = resetTokenExpiry; }
    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
