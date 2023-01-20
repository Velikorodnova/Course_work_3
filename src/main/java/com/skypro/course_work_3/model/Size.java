package com.skypro.course_work_3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {

    S("size", 23),
    M("size", 25),
    L("size", 27);
    private final String size;
    private final int valueSize;

    Size(String size, int valueSize) {
        this.size = size;
        this.valueSize = valueSize;
    }

    @JsonValue
    public int getValueSize() {
        return valueSize;
    }

    @JsonCreator
    public Size forValues(String size, int valueSize) {
        for (Size s : Size.values()) {
            if (s.size.equals(size) && Integer.compare(s.valueSize, valueSize) == 0) {
                return s;
            }
        }
        return null;
    }
}
