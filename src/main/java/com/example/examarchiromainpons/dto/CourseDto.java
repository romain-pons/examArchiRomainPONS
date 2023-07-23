package com.example.examarchiromainpons.dto;

public class CourseDto {

    private int ID;
    private String clientName;

    private double distance;

    private long nbCourses;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getNbCourses() {
        return nbCourses;
    }

    public void setNbCourses(long nbCourses) {
        this.nbCourses = nbCourses;
    }
}
