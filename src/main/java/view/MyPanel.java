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
    private boolean isRectangle = true;

    // Новые простые переменные для цвета и заливки
    public Color currentColor = Color.BLACK;
    public boolean isFilled = true;

    // Список, куда студент складывает все готовые фигуры
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

    // Этот метод вызывается в самом конце рисования фигуры
    public void mouseReleased() {
        if (points[0] != null && points[1] != null) {
            currentShape.setFrameFromDiagonal(points[0], points[1]);

            // Создаем новую фигуру и запоминаем её настройки
            MyShape newShape = new MyShape(currentShape, currentColor, isFilled);
            list.add(newShape);

            // Стираем временные точки, чтобы не дублировать
            points[0] = null;
            points[1] = null;
        }
    }

    public void toggleShape(){
        isRectangle = !isRectangle;
        currentShape = isRectangle ? rectangle2D : ellipse2D;
    }

    public void draw(Graphics2D g2) {
        // 1. Рисуем циклом всё, что мы сохранили в список ранее
        for (int i = 0; i < list.size(); i++) {
            MyShape s = list.get(i);
            g2.setColor(s.color);
            if (s.isFilled) {
                g2.fill(s.shape);
            } else {
                g2.draw(s.shape);
            }
        }

        // 2. Рисуем ту фигуру, которую прямо сейчас тащим мышкой
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
