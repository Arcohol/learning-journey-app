package com.group89.app.model;

public class Role {

    private String semester;

    private String type;

    private String details;

    public Role(String semester, String type, String details) {
        this.semester = semester;
        this.type = type;
        this.details = details;
    }

    public Role() {
        semester = "";
        type = "";
        details = "";
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Role{" +
                "semester='" + semester + '\'' +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
