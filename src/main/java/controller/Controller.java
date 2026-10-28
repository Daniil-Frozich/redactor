package controller;

import model.Model;
import view.MyFrame;
import view.MyPanel;

import java.awt.*;

public class Controller {
    MyPanel myPanel;
    MyFrame myFrame;


    public Model model;

    // Текущие цвета и настройки
    public Color currentColor = Color.BLACK;
    public boolean isFilled = true;

    public Controller() {
        model = new Model(); // Инициализируем модель
        myPanel = new MyPanel(this);
        myFrame = new MyFrame(myPanel);
    }

    public void mousePressed(Point point) {
        model.mousePressed(point);
    }

    public void mouseDragged(Point point) {
        model.mouseDragged(point);
    }

    public void mouseReleased() {
        model.mouseReleased(currentColor, isFilled);
    }

    public void toggleShape(){
        model.toggleShape();
    }

    public void clearList() {
        model.clearList();
    }

    public void draw(Graphics2D g2) {
        // Вызываем отрисовку из модели, передавая текущий цвет и заливку
        model.draw(g2, currentColor, isFilled);
    }
}
