package com.crayon.easysmokes.model;

public enum SideType {
    T("terrorist"), CT("counter-terrorist"), U("either");

    private String name;

    SideType(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
}
