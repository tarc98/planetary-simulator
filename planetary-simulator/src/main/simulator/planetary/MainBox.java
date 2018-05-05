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
        for(int i=0; i<GV.animationSpeed && GV.animate; i++) {
            while(PS.ConnectCollidedPlanets());
            PS.Simulate(GV.timePeroid);
        }

        for(int i=0; i<PS.planets.size(); i++) {
            Planet planet=PS.planets.get(i);
            planet.updateCircle();
        }
        PlanetaryBottomBar.update();
    }

    public static void addEvents(PlanetarySystem PS, Pane window) {
        window.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if(event.getButton()==MouseButton.PRIMARY) {
                Planet planet=new Planet(event.getX() / GV.SCALE, event.getY() / GV.SCALE, 0, 0, Planet.NAME.OurSun);
                planet.updateCircle();
                PS.AddPlanet(planet);

            }
        });
    }
}
