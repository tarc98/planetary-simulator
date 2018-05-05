package main.simulator.planetary;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.simulator.planetary.PlanetaryExceptions.MenuException;

import java.util.Random;

public class PlanetaryGUI {
   // PlanetaryMenuBar menuBar;

    //Zwraca objekt typu MenuBar, który jest wyposażony w opcje
    public static PlanetaryMenuBar makePlanetaryBar() throws MenuException {
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

        return menuBar;
    }

    public static HBox makeRightBox() {
        HBox box=new HBox();
        box.setMinWidth(250);
        ListView<String> listView=new ListView<>();

        box.getChildren().addAll(listView);

        return box;
    }

    public static VBox makeBottomBox() {
        VBox box=new VBox();
        box.setMinHeight(15);

        box.getChildren().addAll();

        return box;
    }

    public static ContextMenu makeContextMenu() {
        ContextMenu contextMenu=new ContextMenu();
        MenuItem add=new MenuItem("Add Entity...");
        MenuItem remove=new MenuItem("Remove Entity");
        remove.setDisable(true);

        contextMenu.getItems().addAll(add, remove);

        return contextMenu;
    }

    public static EventHandler<ContextMenuEvent> onContextMenuMainBoxEventHandler() {
        ContextMenu contextMenu=makeContextMenu();
        return e->{
            contextMenu.show(Main.mainBox, e.getScreenX(), e.getScreenY());
        };
    }

    public static Pane makeMainBox() {
        Pane center=new Pane();
        PlanetarySystem PS = new PlanetarySystem();
        MainBox.addEvents(PS, center);

        EventHandler<ContextMenuEvent> contextMenuEvent=PlanetaryGUI.onContextMenuMainBoxEventHandler();
        center.setOnContextMenuRequested(contextMenuEvent);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(GV.MILLIS), e->{
            MainBox.update(PS, center);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return center;
    }
}
