package com.skypro.course_work_3.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {

    S("size", 23),
    M("size", 25),
    L("size", 27);
    private final String valueSize;
    private int size;

    Size(String valueSize, int size) {
        this.valueSize = valueSize;
        this.size = size;
    }

    @JsonValue
    public int getSize() {
        return size;
    }
}
