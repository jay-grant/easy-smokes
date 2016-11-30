package com.crayon.easysmokes.model;

public enum LevelName {

    DUST2("de_dust2", "dust"), MIRAGE("de_mirage", "mirage"), INFERNO("de_inferno", "inferno"),
    CACHE("de_cache", "cache"), OVERPASS("de_overpass", "overpass"), TRAIN("de_train", "train"),
    COBBLE("de_cobble", "cobble");

    private String name;
    private String nickName;

    LevelName(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }
}
