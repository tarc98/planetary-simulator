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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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

    public final String TITLE="Planetary Simulator";
    public final String VERSION="0.1";
    public int width=1280;
    public int height=720;
    public Color background=Color.LIGHTGREY;
    public double x, y;

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(TITLE+" v. "+VERSION);
        mainStage=stage;

        BorderPane layout=new BorderPane();
        Scene scene=new Scene(layout, width, height);

        EventHandler<ActionEvent> e=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        };

        PlanetaryMenuBar menuBar=new PlanetaryMenuBar();
        menuBar.addMenu("File");
        menuBar.addMenuItem("File", "Save...", null);
        menuBar.addMenuItem("File", "Save as...", null);
        menuBar.addSeparator("File");
        menuBar.addMenuItem("File", "Exit", BasicEvents.closeEvent);
        menuBar.addMenu("Edit");
        menuBar.addMenuItem("Edit", "Undo", null);
        menuBar.addMenuItem("Edit", "Redo", null);
        menuBar.addSeparator("Edit");
        menuBar.addMenuItem("Edit", "Cut", null);
        menuBar.addMenuItem("Edit", "Copy", null);
        menuBar.addMenuItem("Edit", "Paste", null);
        menuBar.addMenu("Help");
        menuBar.addMenuItem("Help", "About", null);

        AnchorPane center=new AnchorPane();
        HBox right=new HBox();
        right.setMinWidth(250);
        Separator sep1=new Separator(Orientation.VERTICAL);

        right.getChildren().addAll(sep1);

        VBox bottom=new VBox();
        bottom.setMinHeight(15);
        bottom.getChildren().addAll(new Separator(Orientation.HORIZONTAL));

        layout.setTop(menuBar);
        layout.setRight(right);
        layout.setCenter(center);
        layout.setBottom(bottom);

        stage.setScene(scene);
        stage.show();
    }
}
