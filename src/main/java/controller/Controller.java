package controller;

import model.MyShape;
import view.MyFrame;
import view.MyPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Controller {
    MyPanel myPanel;
    MyFrame myFrame;
    public Point2D[] points;
    Rectangle2D rectangle2D;
    Ellipse2D ellipse2D;
    RectangularShape currentShape;
    public boolean isRectangle = true; // Сделали public, чтобы панель видела тип фигуры

    public Color currentColor = Color.BLACK;
    public boolean isFilled = true;

    public ArrayList<MyShape> list = new ArrayList<>();

    public Controller() {
        rectangle2D = new Rectangle2D.Double();
        ellipse2D = new Ellipse2D.Double();
        currentShape = rectangle2D;
        points = new Point2D[2];
        myPanel = new MyPanel(this);
        myFrame = new MyFrame(myPanel);
    }

    public void mousePressed(Point point) {
        points[0] = point;
        points[1] = point;
    }

    public void mouseDragged(Point point) {
        points[1] = point;
    }

    public void mouseReleased() {
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

    // Простейший студенческий метод очистки списка
    public void clearList() {
        list.clear();
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < list.size(); i++) {
            MyShape s = list.get(i);
            g2.setColor(s.color);
            if (s.isFilled) {
                g2.fill(s.shape);
            } else {
                g2.draw(s.shape);
            }
        }

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
