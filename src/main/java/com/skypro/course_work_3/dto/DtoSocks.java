package com.skypro.course_work_3.dto;

import com.skypro.course_work_3.model.Color;
import com.skypro.course_work_3.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoSocks {
    private Color color;
    private Size size;
    private int cottonPart;
    private int quantity;

    @Override
    public String toString() {
        return "DtoSocks{" +
                "color=" + color +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
