package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel {
    JTextField t1;
    JTextField t2;
    JTextField t3;

    JButton button;
    JButton dogbutton;
    JButton switchButton;
    JButton clearButton;

    Controller controller;

    public MyPanel(Controller controller) {
        this.controller = controller;


        t1 = new JTextField("Figura: Rectangle", 12);
        t2 = new JTextField("Zalivka: VKL", 10);
        t3 = new JTextField("Cvet: Cherny", 10);

        button = new JButton("Pomenat Cvet");
        dogbutton = new JButton("Zalivka Vkl/Vykl");
        switchButton = new JButton("Perecluchit");
        clearButton = new JButton("Ochistit");


        add(t1);
        add(t2);
        add(t3);


        add(button);
        add(dogbutton);
        add(switchButton);
        add(clearButton);

        this.setBackground(Color.LIGHT_GRAY);

        // Обработка мышки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.mousePressed(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
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

        // Кнопка изменения цвета
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.currentColor == Color.BLACK) {
                    controller.currentColor = Color.RED;
                    t3.setText("Cvet: Krasny");
                } else if (controller.currentColor == Color.RED) {
                    controller.currentColor = Color.BLUE;
                    t3.setText("Cvet: Siniy");
                } else {
                    controller.currentColor = Color.BLACK;
                    t3.setText("Cvet: Cherny");
                }
            }
        });

        // Кнопка изменения заливки
        dogbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.isFilled = !controller.isFilled;
                if (controller.isFilled) {
                    t2.setText("Zalivka: VKL");
                } else {
                    t2.setText("Zalivka: VYKL");
                }
            }
        });

        // Кнопка переключения фигуры
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.toggleShape();

                if (controller.model.isRectangle) {
                    t1.setText("Figura: Rectangle");
                } else {
                    t1.setText("Figura: Ellipse");
                }
                repaint();
            }
        });


        // Кнопка очистки холста
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clearList();
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
