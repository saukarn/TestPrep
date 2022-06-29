package com.saurabh.covid19tracker.models;

public class LocationStats {
    private String State;
    private int ConfirmedCases;
    private int Deaths;
    private String County;
    private int diffFromPrevDay;

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }



    public LocationStats() {
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }



    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getConfirmedCases() {
        return ConfirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        ConfirmedCases = confirmedCases;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int deaths) {
        Deaths = deaths;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "State='" + State + '\'' +
                ", ConfirmedCases=" + ConfirmedCases +
                ", County='" + County + '\'' +
                ", diffFromPrevDay=" + diffFromPrevDay +
                '}';
    }
}
