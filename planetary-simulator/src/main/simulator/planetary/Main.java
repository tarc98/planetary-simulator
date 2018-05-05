package main.simulator.planetary;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;


import javafx.geometry.Bounds;
import java.util.Random;


public class Main extends Application {
    public final String TITLE="Planetary Simulator";
    public final String VERSION="0.1";
    public int width=1280;
    public int height=720;
    public Color background=Color.LIGHTGREY;
    public double x, y;

    public static Stage mainStage;

    public static PlanetaryMenuBar menuBar;
    public static HBox rightBox;
    public static Pane bottomBox;
    public static Pane mainBox;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage=stage;
        stage.setTitle(TITLE+" v. "+VERSION);

        BorderPane layout=new BorderPane();
        Scene scene=new Scene(layout, width, height);
        GlobalEvents.addEvents(scene);

        menuBar=PlanetaryGUI.makePlanetaryBar();
        rightBox=PlanetaryGUI.makeRightBox();
        bottomBox=PlanetaryGUI.makeBottomBox();
        mainBox=PlanetaryGUI.makeMainBox();

        layout.setCenter(mainBox);
        layout.setTop(menuBar);
        layout.setRight(rightBox);
        layout.setBottom(bottomBox);

        stage.setScene(scene);
        stage.show();
    }
}
