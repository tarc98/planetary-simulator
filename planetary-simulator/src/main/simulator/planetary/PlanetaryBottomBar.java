package main.simulator.planetary;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class PlanetaryBottomBar {

    public static TextField mass;
    public static TextField radius;
    public static Button playButton;
    public static Slider speedSlider;
    public static Slider velocityScaleSlider;

    public static void setTextFields(TextField m, TextField r) {
        mass=m;
        radius=r;
    }

    public static void setPlayButton(Button b) {
        playButton=b;
    }

    public static void setSpeedSlider(Slider s) {
        speedSlider=s;
    }

    public static void setVelocityScaleSlider(Slider s) {
        velocityScaleSlider=s;
    }

    public static double getMass() {
        return Double.parseDouble(mass.getText());
    }
    public static double getRadius() {
        return Double.parseDouble(radius.getText());
    }

    public static void setPlayButtonEvent(Button button) {
        button.setOnAction(e->{
            if(GV.animate) {
                GlobalEvents.setPause();
            }
            else {
                GlobalEvents.setPlay();
            }
        });
    }
    public static double getAnimationSpeed() {
        return speedSlider.getValue()*speedSlider.getValue();
    }
    public static double getVelocityScale() {
        return velocityScaleSlider.getValue()*velocityScaleSlider.getValue();
    }
}
