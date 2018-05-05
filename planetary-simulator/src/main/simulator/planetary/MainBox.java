package main.simulator.planetary;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MainBox {

    public static void update(PlanetarySystem PS, Pane window, double timePeroid, double SCALE) {
        PS.Simulate(timePeroid);
        while(PS.ConnectCollidedPlanets());
        window.getChildren().clear();

        for(int i=0; i<PS.planets.size(); i++) {
            Circle ball = new Circle(PS.planets.get(i).radius * SCALE*10, PS.planets.get(i).c);
            ball.relocate(PS.planets.get(i).x_pos * SCALE, PS.planets.get(i).y_pos * SCALE);
            window.getChildren().add(ball);
        }
    }

    public static void addEvents(PlanetarySystem PS, Pane window, double SCALE) {
        window.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            PS.AddPlanet(new Planet(event.getX()/SCALE, event.getY()/SCALE, 0, 0,0,0,Planet.NAME.OurSun ));
        });
    }
}
