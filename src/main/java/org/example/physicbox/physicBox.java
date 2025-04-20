package org.example.physicbox;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class physicBox extends Application {
    private int dir = 1;
    @Override
    public void start(Stage stage) {
        // Создаем квадрат
        Rectangle square = new Rectangle(50,50,50,50);
        Rectangle square2 = new Rectangle(50,50,50,50);
        square.setFill(Color.MEDIUMPURPLE); // Цвет заливки
        square2.setFill(Color.PURPLE); // Цвет заливки
        //square.setStroke(Color.BLACK); // Цвет границы
        //square.setStrokeWidth(2);      // Толщина границы
        square.setArcHeight(10); square.setArcWidth(10);
        square2.setArcHeight(10); square2.setArcWidth(10);
        square.setX(150); square.setY(150);
        square2.setX(600); square2.setY(150);
        square.setOnMouseClicked(mouseEvent -> {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(8), actionEvent -> {
                    double newX = square.getX() + dir;
                    square.setX(newX);

                    if(square.getX()==square2.getX()){
                        dir *= -1;
                    }
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный "цикл"
            timeline.play();
            square.setOnMouseClicked(null);
        });

        // Размещаем квадрат на панели
        Pane root = new Pane(square, square2);
        Scene scene = new Scene(root, 840, 420);

        stage.setTitle("Квадрат в JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(); // Запуск приложения
    }
}