package main.simulator.planetary;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlanetaryBottomBar {

    public static TextField mass;
    public static TextField radius;

    public static void setTextFields(TextField m, TextField r) {
        mass=m;
        radius=r;
    }

    public static double getMass() {
        return Double.parseDouble(mass.getText());
    }
    public static double getRadius() {
        return Double.parseDouble(radius.getText());
    }
}
