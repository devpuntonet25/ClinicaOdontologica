package model;

public class Dentist {

    private Integer id;
    private Integer registration;
    private String firstName;
    private String lastName;

    public Dentist() {

    }

    public Dentist(Integer id, Integer registration, String firstName, String lastName) {
        this.id = id;
        this.registration = registration;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Dentist(Integer registration, String firstName, String lastName) {
        this.registration = registration;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", registration=" + registration +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
