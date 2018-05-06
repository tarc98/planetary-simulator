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
    public static void addEvents(Scene scene) {
        scene.addEventHandler(KeyEvent.ANY, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if(!(MainBox.mode.get() == 1)) {
                    if (GV.animate)
                        GV.animate = false;
                    else
                        GV.animate = true;
                }
            }

            if(event.getCode() == KeyCode.ESCAPE){
                if(MainBox.mode.get() == 1)
                {
                    MainBox.drawLine = false;
                    MainBox.escapeLine = true;
                    MainBox.mode.set(0);
                    GV.animate=MainBox.before.get();
                }
            }
        });



    }


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
}
