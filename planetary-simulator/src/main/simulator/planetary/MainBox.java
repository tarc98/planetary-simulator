package main.simulator.planetary;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseButton;
import javafx.scene.input.KeyCode;

public class MainBox {
    public static void update(PlanetarySystem PS, Pane window) {

        for(int i=0; i<GV.animationSpeed && GV.animate; i++)
        {
            PS.Simulate(GV.timePeroid);
            while(PS.ConnectCollidedPlanets());
        }

        window.getChildren().clear();

        for(int i=0; i<PS.planets.size(); i++) {
            Circle ball = new Circle(PS.planets.get(i).radius * GV.SCALE*10, PS.planets.get(i).c);
            ball.setCenterX(PS.planets.get(i).x_pos * GV.SCALE);
            ball.setCenterY(PS.planets.get(i).y_pos * GV.SCALE);
            window.getChildren().add(ball);
        }
    }

    public static void addEvents(PlanetarySystem PS, Pane window) {
        window.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            PS.AddPlanet(new Planet(event.getX()/GV.SCALE, event.getY()/GV.SCALE, 0, 0,0,0,Planet.NAME.OurSun ));
        });
    }
}
