package main.simulator.planetary;

import javafx.scene.control.Label;

public class PlanetaryBottomBar {

    public static Double days;
    public static Label label;

    public static void update() {
        if(GV.animate) {
            days += GV.animationSpeed * GV.timePeroid / 86400;
            label.setText(days.intValue() + " days " + ((int) (days * 24)) % 24 + " hours");
        }
    }

    public static void setValues(Double _days, Label _label) {
        label=_label;
        days=_days;
    }
}
