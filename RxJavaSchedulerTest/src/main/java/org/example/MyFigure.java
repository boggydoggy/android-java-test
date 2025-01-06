package org.example;

public class MyFigure {


    private String color;
    private String shape;

    public MyFigure(String color, String shape) {
        this.color = color;
        this.shape = shape;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                '}';
    }
}
