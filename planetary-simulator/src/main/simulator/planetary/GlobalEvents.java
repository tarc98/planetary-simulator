package main.simulator.planetary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.swing.*;


public class GlobalEvents {

    public static EventHandler<ActionEvent> closeEvent() {
        return e->{
            Main.mainStage.close();
        };
    }

    public static Planet addPlanet(PlanetarySystem PS, double x, double y) {
        Planet planet=new Planet(x / GV.SCALE, y/ GV.SCALE, 0, 0,
                PlanetaryBottomBar.getMass()*PlanetPreset.OurSun.mass,
                PlanetaryBottomBar.getRadius()*PlanetPreset.OurSun.radius);
        planet.updateCircle();
        PS.AddPlanet(planet);
        return planet;
    }

    public static EventHandler<MouseEvent> drawLine(double x, double y, PlanetarySystem PS, Line line) {
        return e->{
            Main.mainBox.getChildren().remove(line);
            line.setStartX(x);
            line.setStartY(y);
            line.setEndX(e.getX());
            line.setEndY(e.getY());
            Main.mainBox.getChildren().add(line);
            PS.Draw();
        };
    }

    public static EventHandler<ActionEvent> clear() {
        return e-> {
            PlanetarySystem PS=PlanetaryGUI.mainSystem;
            for (int i = 0; i < PS.planets.size(); i++) {
                PS.planets.get(i).removeCircle();
            }
            PS.planets.clear();
        };
    }

    public static void setPause() {
        GV.animate=false;
        PlanetaryBottomBar.playButton.setText("PLAY");
    }

    public static void setPlay() {
        GV.animate=true;
        PlanetaryBottomBar.playButton.setText("PAUSE");
    }
}
