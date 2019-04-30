package com.bot.service.botservice.model;

public class CaseModel {
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String description;

    public void setName(String name) {
        this.name = name;

    }
    public void setPhone(String phone) {
        this.phone = phone;

    }
    public void setSubject(String subject) {
        this.subject = subject;

    }
    public void setEmail(String email) {
        this.email = email;

    }
    public void setDescription(String description) {
        this.description = description;

    }

    public String getName() {
        return this.name;

    }
    public String getPhone() {
        return this.phone;

    }
    public String getSubject() {
        return this.subject;

    }
    public String getEmail() {
        return this.email;

    }
    public String getDescription() {
        return this.description;

    }

}
