package com.skypro.course_work_3.model;

import net.minidev.json.JSONUtil;

public class Socks {
    private Color color;
    private Size size;
    private Integer cottonPart;

    public Socks(Color color, Size size, Integer cottonPart) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public String toString() {
        return "Цвет - " + color +
                ", Размер - " + size +
                ", Количество хлопка - " + cottonPart;
    }

    public void SysUser(String both) {
        SysUser user = JSONUtil.toBean(both, SysUser.class);
        BeanUtil.copyProperties(user, this);
    }
}
