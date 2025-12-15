package com.example.studentregister.data.entities;

public class UnitData {
    private String unitNumber;
    private String unitName;
    private String ra;
    private String course;
    private String currentSemester;
    private String coursePeriod;
    private String entrySemesterYear;

    public UnitData(String unitNumber, String unitName, String ra, String course, String currentSemester,
                    String coursePeriod, String entrySemesterYear) {
        this.unitNumber = unitNumber;
        this.unitName = unitName;
        this.ra = ra;
        this.course = course;
        this.currentSemester = currentSemester;
        this.coursePeriod = coursePeriod;
        this.entrySemesterYear = entrySemesterYear;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getEntrySemesterYear() {
        return entrySemesterYear;
    }

    public void setEntrySemesterYear(String entrySemesterYear) {
        this.entrySemesterYear = entrySemesterYear;
    }
}

