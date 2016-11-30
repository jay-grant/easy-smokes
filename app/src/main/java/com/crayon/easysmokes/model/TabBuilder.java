package com.crayon.easysmokes.model;

public class TabBuilder {

    String dispName;
    SideType side;
    int colour;

    public TabBuilder(String dispName, SideType side, int colour) {
        this.dispName = dispName;
        this.side = side;
        this.colour = colour;
    }

    public String getDispName() {
        return dispName;
    }

    public SideType getSide() {
        return side;
    }

    public int getColour() {
        return colour;
    }
}
