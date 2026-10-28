package model;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class MyShape {
    public RectangularShape shape;
    public Color color;
    public boolean isFilled; // true - закрашенная, false - пустая

    public MyShape(RectangularShape shape, Color color, boolean isFilled) {
        // Делаем копию фигуры (.clone()), чтобы старые фигуры не двигались за мышкой
        this.shape = (RectangularShape) shape.clone();
        this.color = color;
        this.isFilled = isFilled;
    }
}

