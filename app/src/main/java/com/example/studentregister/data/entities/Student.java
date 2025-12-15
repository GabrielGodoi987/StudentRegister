package com.example.studentregister.data.entities;

import com.example.studentregister.data.entities.enums.SexEnum;

import java.util.Objects;

public class Student {
    private Integer id;
    private String name;
    private String cpf;
    private SexEnum sex;
    private String nationality;
    private String naturality;
    private String documentName;
    private String documentNumber;
    private Address address;
    private ContactData contactData;
    private UnitData unitData;


    public Student(
            Integer id,
            String name,
            String cpf,
            SexEnum sex,
            String nationality,
            String naturality,
            String documentName,
            String documentNumber,
            Address address
    ) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.sex = sex;
        this.nationality = nationality;
        this.naturality = naturality;
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.address = address;
    }

    public Student(
            Integer id,
            String name,
            String cpf,
            SexEnum sex,
            String nationality,
            String naturality,
            String documentName,
            String documentNumber,
            Address address,
            ContactData contactData,
            UnitData unitData
    ) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.sex = sex;
        this.nationality = nationality;
        this.naturality = naturality;
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.address = address;
        this.contactData = contactData;
        this.unitData = unitData;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNaturality() {
        return naturality;
    }

    public void setNaturality(String naturality) {
        this.naturality = naturality;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public UnitData getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitData unitData) {
        this.unitData = unitData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
