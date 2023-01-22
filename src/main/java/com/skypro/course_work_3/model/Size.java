package com.skypro.course_work_3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {

    S(23),
    M(25),
    L(27);
    private final int valueSize;
    Size(int valueSize) {
        this.valueSize = valueSize;
    }

    @JsonValue
    public int getValueSize() {
        return valueSize;
    }

    @JsonCreator
    public Size forValues(int valueSize) {
        for (Size s : Size.values()) {
            if (s.valueSize == valueSize) {
                return s;
            }
        }
        return null;
    }
}
