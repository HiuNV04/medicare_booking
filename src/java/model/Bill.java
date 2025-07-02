/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Bill {

    private int id;
    private int patientId;
    private String patientName;
    private Timestamp appointmentTime;
    private String paymentMethod;
    private String paymentStatus;
    private double amount;

    private String testName;
    private int quantity;
    private double unitPrice;
    private double totalTest;

    public Bill() {
    }

    public Bill(int id, int patientId, String patientName, Timestamp appointmentTime, String paymentMethod, String paymentStatus, double amount, String testName, int quantity, double unitPrice, double totalTest) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.appointmentTime = appointmentTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.testName = testName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalTest = totalTest;
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

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(double totalTest) {
        this.totalTest = totalTest;
    }

}
