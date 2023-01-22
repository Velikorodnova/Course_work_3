package com.skypro.course_work_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;

    @Override
    public String toString() {
        return "Socks{" +
                "color=" + color +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                '}';
    }
}