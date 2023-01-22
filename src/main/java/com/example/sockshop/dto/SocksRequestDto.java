package com.example.sockshop.dto;

import com.example.sockshop.model.Color;
import com.example.sockshop.model.Size;
import com.example.sockshop.model.Sock;

import java.awt.*;

public class SocksRequestDto {

   private Color color;

   private Size size;

   private int cottonRatio;

   private int quantity;

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

    public int getCottonRatio() {
        return cottonRatio;
    }

    public void setCottonRatio(int cottonRatio) {
        this.cottonRatio = cottonRatio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
