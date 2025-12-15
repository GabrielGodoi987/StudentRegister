package com.example.studentregister.data.entities;

public class EmergencyContact {

    private String name;
    private String relationship;
    private String mobilePhone;

    public EmergencyContact(
            String name,
            String relationship,
            String mobilePhone
    ) {
        this.name = name;
        this.relationship = relationship;
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
