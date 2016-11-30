package com.crayon.easysmokes.model;

public enum NadeType {
    SMOKE("smoke"), FIRE("fire"), FLASH("flash"), FRAG("frag"), DECOY("decoy");

    private String name;

    NadeType(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
}
