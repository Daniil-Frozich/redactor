package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel {
    JTextField t1;
    JTextField t2;
    JButton button;       // Меняет цвет
    JButton dogbutton;    // Меняет заливку
    JButton switchButton; // Переключает фигуру

    Controller controller;

    public MyPanel(Controller controller) {
        this.controller = controller;

        t1 = new JTextField("", 10);
        t2 = new JTextField("", 10);
        button = new JButton("Pomenat Cvet");
        dogbutton = new JButton("Zalivka Vkl/Vykl");
        switchButton = new JButton("Perecluchit");

        add(t1);
        add(t2);
        add(button);
        add(dogbutton);
        add(switchButton);

        this.setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.mousePressed(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                // Когда отпускаем мышку — сохраняем фигуру в список
                controller.mouseReleased();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                controller.mouseDragged(e.getPoint());
                repaint();
            }
        });

        // Кнопка переключения цвета (Черный -> Красный -> Синий -> Черный)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.currentColor == Color.BLACK) {
                    controller.currentColor = Color.RED;
                    t1.setText("Cvet: Krasny");
                } else if (controller.currentColor == Color.RED) {
                    controller.currentColor = Color.BLUE;
                    t1.setText("Cvet: Siniy");
                } else {
                    controller.currentColor = Color.BLACK;
                    t1.setText("Cvet: Cherny");
                }
            }
        });

        // Кнопка включения и выключения заливки фигуры
        dogbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.isFilled = !controller.isFilled; // меняет true на false и наоборот
                if (controller.isFilled) {
                    t2.setText("Zalivka: VKL");
                } else {
                    t2.setText("Zalivka: VYKL");
                }
            }
        });

        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.toggleShape();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.draw(g2);
    }
}
