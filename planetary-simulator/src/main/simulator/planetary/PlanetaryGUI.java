package main.simulator.planetary;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.simulator.planetary.PlanetaryExceptions.MenuException;

import java.util.Random;

public class PlanetaryGUI {

    public static TextField mass;
    public static TextField radius;
    public static Line line;
    public static PlanetarySystem mainSystem;
    public static CheckMenuItem velocityVector;

    //Zwraca objekt typu MenuBar, który jest wyposażony w opcji
    public static MenuBar makePlanetaryBar() throws MenuException {
        MenuBar menuBar=new MenuBar();
        Menu edit=new Menu("Edit");
        MenuItem clear=new MenuItem("Reset");
        CheckMenuItem velVector=new CheckMenuItem("Set velocity vector");
        CheckMenuItem showSpeed=new CheckMenuItem("Show speed");

        MainBox.setVelocityVector=true;
        clear.setOnAction(GlobalEvents.clear());
        velVector.setSelected(true);
        showSpeed.setSelected(false);
        velVector.setOnAction(e->{
            if(MainBox.setVelocityVector==true) MainBox.setVelocityVector=false;
            else MainBox.setVelocityVector=true;
        });
        showSpeed.setOnAction(e->{
            if(MainBox.showSpeed) {
                MainBox.showSpeed=false;
                PlanetaryGUI.mainSystem.removeLabels();
            }
            else {
                MainBox.showSpeed=true;
                PlanetaryGUI.mainSystem.setLabels();
            }
        });


        edit.getItems().addAll(clear,showSpeed, velVector);

        menuBar.getMenus().addAll(edit);

        return menuBar;
    }

    public static HBox makeRightBox() {
        HBox box=new HBox();
        box.setMinWidth(250);
        ListView<String> listView=new ListView<>();

        box.getChildren().addAll(listView);

        return box;
    }

    public static HBox makeBottomBox() {
        Double days=new Double(0);
        HBox box=new HBox();
        box.setMinHeight(40);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(20);
        box.setStyle("-fx-background-color: #d0d3d4;");
        box.setPadding(new Insets(0,0,0,20));

        Label massLabel=new Label("Mass:");
        Label radiusLabel=new Label("Radius:");
        Label speedLabel=new Label("Animation\nspeed:");
        Label velocityLabel=new Label("Speed\nscale:");
        Button play=new Button("PAUSE");
        Slider speed=new Slider();
        Slider velocityScale=new Slider();

        speed.setMin(0.5);
        speed.setMax(60);
        speed.setValue(30);
        velocityScale.setMin(0);
        velocityScale.setMax(10);
        velocityScale.setValue(3);
        play.setMinWidth(70);
        speedLabel.setFont(new Font(11));
        velocityLabel.setFont(new Font(11));

        PlanetaryBottomBar.setPlayButton(play);
        PlanetaryBottomBar.setPlayButtonEvent(play);
        PlanetaryBottomBar.setSpeedSlider(speed);
        PlanetaryBottomBar.setVelocityScaleSlider(velocityScale);

        mass=new TextField("1");
        radius=new TextField("1");

        PlanetaryBottomBar.setTextFields(mass, radius);
        box.getChildren().addAll(massLabel,  mass, radiusLabel, radius, speedLabel, speed, velocityLabel, velocityScale, play);

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
        line=new Line();


        PlanetarySystem PS = new PlanetarySystem();
        mainSystem=PS;
        MainBox.setup(PS, center);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(GV.MILLIS), e->{
            MainBox.update(PS, center);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        MainBox.timeLabel=new Label();
        MainBox.timeLabel.setTranslateX(10);
        MainBox.timeLabel.setTranslateY(10);

        return center;
    }
}
