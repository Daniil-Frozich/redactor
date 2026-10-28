package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel {
    JTextField t1; // Для фигуры
    JTextField t2; // Для заливки
    JTextField t3; // НОВОЕ ПОЛЕ: Для цвета

    JButton button;       // Меняет цвет
    JButton dogbutton;    // Меняет заливку
    JButton switchButton; // Переключает фигуру
    JButton clearButton;  // Очищает поле

    Controller controller;

    public MyPanel(Controller controller) {
        this.controller = controller;

        // Создаем текстовые поля с начальным текстом
        t1 = new JTextField("Figura: Rectangle", 12);
        t2 = new JTextField("Zalivka: VKL", 10);
        t3 = new JTextField("Cvet: Cherny", 10); // Инициализируем новое поле цвета

        button = new JButton("Pomenat Cvet");
        dogbutton = new JButton("Zalivka Vkl/Vykl");
        switchButton = new JButton("Perecluchit");
        clearButton = new JButton("Ochistit");

        // Добавляем все три поля на панель
        add(t1);
        add(t2);
        add(t3); // Добавили поле цвета на экран

        // Добавляем кнопки
        add(button);
        add(dogbutton);
        add(switchButton);
        add(clearButton);

        this.setBackground(Color.LIGHT_GRAY);

        // Обработка мышки (нажатие, отпускание, перемещение)
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

        // Кнопка изменения цвета (теперь пишет изменения строго в свое поле t3)
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

        // Кнопка изменения заливки (пишет строго в t2)
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

        // Кнопка переключения фигуры (пишет строго в t1)
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.toggleShape();
                // Обращаемся к переменной через модель внутри контроллера
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
