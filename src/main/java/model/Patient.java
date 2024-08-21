package model;

import java.time.LocalDate;

public class Patient {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cardIdentity;
    private LocalDate admissionOfDate;

    public Patient(String firstName, String lastName, String cardIdentity, LocalDate admissionOfDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardIdentity = cardIdentity;
        this.admissionOfDate = admissionOfDate;
    }

    public Patient(Integer id, String firstName, String lastName, String cardIdentity, LocalDate admissionOfDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardIdentity = cardIdentity;
        this.admissionOfDate = admissionOfDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardIdentity() {
        return cardIdentity;
    }

    public void setCardIdentity(String cardIdentity) {
        this.cardIdentity = cardIdentity;
    }

    public LocalDate getAdmissionOfDate() {
        return admissionOfDate;
    }

    public void setAdmissionOfDate(LocalDate admissionOfDate) {
        this.admissionOfDate = admissionOfDate;
    }
}
