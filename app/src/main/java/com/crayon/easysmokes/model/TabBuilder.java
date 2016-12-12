package com.crayon.easysmokes.model;

public class TabBuilder {

    String dispName;
    String sideID;
    int colour;

    public TabBuilder(String dispName, String sideID, int colour) {
        this.dispName = dispName;
        this.sideID = sideID;
        this.colour = colour;
    }

    public String getDispName() {
        return dispName;
    }

    public String getSide() {
        return sideID;
    }

    public int getColour() {
        return colour;
    }
}
