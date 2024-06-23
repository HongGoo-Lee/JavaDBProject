package model;

import java.sql.Date;

public class User {
    private String userId;
    private String password;
    private String name;
    private Date birthDate;
    private String phoneNumber;
    private boolean gender;
    private boolean state;
    private boolean manager;

    public User(String userId, String password, String name, Date birthDate, String phoneNumber, boolean gender, boolean state, boolean manager) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.state = state;
        this.manager = manager;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public boolean isState() {
        return state;
    }

    public boolean isManager() {
        return manager;
    }
}
