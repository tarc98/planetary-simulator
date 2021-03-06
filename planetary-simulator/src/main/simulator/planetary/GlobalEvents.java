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
        planet.addCircle();
        PS.AddPlanet(planet);
        if(MainBox.showSpeed) planet.setSpeedLabel();
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
            PS.removeLabels();
            for (int i = 0; i < PS.planets.size(); i++) {
                PS.planets.get(i).removeCircle();
            }
            PS.planets.clear();
            GV.time=0;
            GV.animate=true;
            MainBox.update(PlanetaryGUI.mainSystem, Main.mainBox);
            setPause();
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

    public static void updateTimeLabel() {
        GV.time+=GV.animationSpeed*PlanetaryBottomBar.getAnimationSpeed();
        if(PlanetaryBottomBar.getAnimationSpeed()>=50) MainBox.timeLabel.setText((int) GV.time/86400 + " days");
        else if(PlanetaryBottomBar.getAnimationSpeed()>=5) MainBox.timeLabel.setText(
                (int) GV.time/86400 + " days " + ((int) GV.time/3600)%24 + " hours");
        else MainBox.timeLabel.setText(
                    (int) GV.time/86400 + " days " + ((int) GV.time/3600)%24 + " hours " + ((int) GV.time/60)%60 + " minutes");
        Main.mainBox.getChildren().remove(MainBox.timeLabel);
        Main.mainBox.getChildren().add(MainBox.timeLabel);
    }
}
