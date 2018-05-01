package main.simulator.planetary;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    public void start(Stage stage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("layout.fxml"));
        Scene scene=new Scene(root, width, height);

        stage.setTitle(TITLE+" v. "+VERSION);
        stage.setScene(scene);

        stage.show();
    }
}
