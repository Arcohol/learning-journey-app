package com.group89.app.model;

public class Outlook {
    private String name;
    private String project;
    private String type;
    private String rank;
    private String country;
    private String cost;

    public Outlook(String name, String project, String type, String rank, String country, String cost) {
        this.name = name;
        this.project = project;
        this.type = type;
        this.rank = rank;
        this.country = country;
        this.cost = cost;
    }

    public Outlook(){
        name = "";
        project = "";
        type = "";
        rank = "";
        country = "";
        cost = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
