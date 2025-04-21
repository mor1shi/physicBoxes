package org.example.physicbox;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class physic2BounceBoxes extends Application {

    private double velX1 = 2.8;
    private double velX2 = -2.2;
    private double elast = 1.0;

    @Override
    public void start(Stage stage) {
        // Создаем квадрат
        Rectangle square = new Rectangle(50,50,50,50);
        Rectangle square2 = new Rectangle(50,50,50,50);
        square.setFill(Color.MEDIUMPURPLE); // Цвет заливки
        square2.setFill(Color.PURPLE); // Цвет заливки
        square.setArcHeight(10); square.setArcWidth(10);
        square2.setArcHeight(10); square2.setArcWidth(10);
        square.setX(150); square.setY(150);
        square2.setX(600); square2.setY(150);

        Pane root = new Pane(square, square2);
        Scene scene = new Scene(root, 840, 420);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Движение объектов
                square.setX(square.getX() + velX1);
                square2.setX(square2.getX() + velX2);

                // Проверка столкновения
                if (square.getBoundsInParent().intersects(square2.getBoundsInParent())) {
                    // Расчет новых скоростей после столкновения
                    double mass1 = 100; // Масса пропорциональна площади
                    double mass2 = 50;

                    double v1 = velX1;
                    double v2 = velX2;

                    // Формула упругого столкновения
                    velX1 = ((mass1 - mass2) * v1 + 2 * mass2 * v2) / (mass1 + mass2) * elast;
                    velX2 = ((mass2 - mass1) * v2 + 2 * mass1 * v1) / (mass1 + mass2) * elast;

                    // Разделяем объекты, чтобы не залипали
                    square.setX(square.getX() + velX1);
                    square2.setX(square2.getX() + velX2);
                }

                // Отскок от границ экрана
                if (square.getX() <= 0 || square.getX() >= 800) velX1 *= -1;
                if (square2.getX() <= 0 || square2.getX() >= 800) velX2 *= -1;
            }
        }.start();
        stage.setTitle("2BounceBoxes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}