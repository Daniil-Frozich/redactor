package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Model {
    // Список всех нарисованных фигур
    public ArrayList<MyShape> list = new ArrayList<>();

    // Массив точек для рисования
    public Point2D[] points;

    // Шаблоны фигур
    Rectangle2D rectangle2D;
    Ellipse2D ellipse2D;
    RectangularShape currentShape;
    public boolean isRectangle = true;

    public Model() {
        rectangle2D = new Rectangle2D.Double();
        ellipse2D = new Ellipse2D.Double();
        currentShape = rectangle2D;
        points = new Point2D[2];
    }

    public void mousePressed(Point point) {
        points[0] = point;
        points[1] = point;
    }

    public void mouseDragged(Point point) {
        points[1] = point;
    }

    public void mouseReleased(Color currentColor, boolean isFilled) {
        if (points[0] != null && points[1] != null) {
            currentShape.setFrameFromDiagonal(points[0], points[1]);
            MyShape newShape = new MyShape(currentShape, currentColor, isFilled);
            list.add(newShape);
            points[0] = null;
            points[1] = null;
        }
    }

    public void toggleShape(){
        isRectangle = !isRectangle;
        currentShape = isRectangle ? rectangle2D : ellipse2D;
    }

    public void clearList() {
        list.clear();
    }

    public void draw(Graphics2D g2, Color currentColor, boolean isFilled) {
        // 1. Рисуем старые фигуры из списка
        for (int i = 0; i < list.size(); i++) {
            MyShape s = list.get(i);
            g2.setColor(s.color);
            if (s.isFilled) {
                g2.fill(s.shape);
            } else {
                g2.draw(s.shape);
            }
        }

        // 2. Рисуем фигуру, которую тянем прямо сейчас
        if (points[0] != null && points[1] != null) {
            currentShape.setFrameFromDiagonal(points[0], points[1]);
            g2.setColor(currentColor);
            if (isFilled) {
                g2.fill(currentShape);
            } else {
                g2.draw(currentShape);
            }
        }
    }
}
