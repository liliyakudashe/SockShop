package com.example.sockshop.model;


import java.util.Objects;

public class Sock {

    private final Color color;
    private final Size size;
    private final int cottonRatio;

    public Sock(Color color, Size size, int cottonRatio) {
        this.color = color;
        this.size = size;
        this.cottonRatio = cottonRatio;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonRatio() {
        return cottonRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonRatio == sock.cottonRatio && color == sock.color && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonRatio);
    }
}
