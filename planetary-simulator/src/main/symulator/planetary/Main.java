package main.symulator.planetary;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class Main extends Application {

    final String TITLE="Planetary Simulator";
    final String VERSION="0.1";
    int width=900;
    int height=600;
    Color background=Color.LIGHTGREY;
    double x, y;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE+" v. "+VERSION);
        Group root=new Group();
        Scene scene=new Scene(root, width, height, background);
        stage.setScene(scene);
        Line line=new Line();
        Group circles=new Group();
        root.getChildren().addAll(circles);

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            if(me.getButton().equals(MouseButton.PRIMARY)) {
                line.setStartY(me.getY());
                line.setStartX(me.getX());
                Circle circle=new Circle(me.getX(), me.getY(), 10, Color.INDIANRED);
                circles.getChildren().add(circle);
            }

        });

        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent me) -> {
            if(me.getButton().equals(MouseButton.PRIMARY)) {
                root.getChildren().remove(line);
                line.setEndX(me.getX());
                line.setEndY(me.getY());
                x=me.getX();
                y=me.getY();
                root.getChildren().add(line);

            }
        });


        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent me) -> {
            if(me.getButton().equals(MouseButton.PRIMARY)) {
                root.getChildren().remove(line);
                circles.getChildren().remove(circles.getChildren().get(circles.getChildren().size()-1));
            }
        });


        stage.show();


    }


}
