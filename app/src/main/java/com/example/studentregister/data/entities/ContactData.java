package com.example.studentregister.data.entities;

public class ContactData {

    private String homePhone;
    private String workPhone;
    private String mobilePhone;
    private String institutionalEmail;
    private String personalEmail;
    private EmergencyContact emergencyContact;

    public ContactData(
            String homePhone,
            String workPhone,
            String mobilePhone,
            String institutionalEmail,
            String personalEmail,
            EmergencyContact emergencyContact
    ) {
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.mobilePhone = mobilePhone;
        this.institutionalEmail = institutionalEmail;
        this.personalEmail = personalEmail;
        this.emergencyContact = emergencyContact;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
