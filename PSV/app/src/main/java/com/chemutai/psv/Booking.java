package com.chemutai.psv;

public class Booking {

    private int id;
    private String route,busCompany, seatNumber, travelDate, pickUpPoint;

    public Booking(int id, String route, String busCompany, String seatNumber, String travelDate, String pickUpPoint) {
        this.id = id;
        this.route = route;
        this.busCompany = busCompany;
        this.seatNumber = seatNumber;
        this.travelDate = travelDate;
        this.pickUpPoint = pickUpPoint;
    }

    public Booking(String route, String busCompany, String seatNumber, String travelDate, String pickUpPoint) {
        this.route = route;
        this.busCompany = busCompany;
        this.seatNumber = seatNumber;
        this.travelDate = travelDate;
        this.pickUpPoint = pickUpPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }
}
