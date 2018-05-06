package main.simulator.planetary;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MainBox {

    public static boolean drawLine;
    public static double mouseX;
    public static double mouseY;
    public static double startX;
    public static double startY;
    public static boolean setVelocityVector;
    static boolean escapeLine;
    static AtomicInteger mode;
    static AtomicBoolean before;
    static AtomicPlanet planet;

    public static void update(PlanetarySystem PS, Pane window) {
        Line line=PlanetaryGUI.line;
        Main.mainBox.getChildren().remove(line);
        if(drawLine) {
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(mouseX);
            line.setEndY(mouseY);
            Main.mainBox.getChildren().add(line);
        }

        for(int i=0; i<GV.animationSpeed && GV.animate; i++) {
            while(PS.ConnectCollidedPlanets());
            PS.Simulate(GV.timePeroid);
        }

        for(int i=0; i<PS.planets.size(); i++) {
            Planet planet=PS.planets.get(i);
            planet.updateCircle();
        }
    }

    public static void addEvents(PlanetarySystem PS, Pane window) {
        mode= new AtomicInteger();
        before=new AtomicBoolean();
        planet=new AtomicPlanet();

        window.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
            Line line=new Line();
            EventHandler<MouseEvent> drawL=GlobalEvents.drawLine(e.getX(), e.getY(), PS, line);
            if(e.getButton()==MouseButton.PRIMARY) {
                if(mode.get() ==0) {
                    planet.set(GlobalEvents.addPlanet(PS, e.getX(), e.getY()));
                    if(setVelocityVector) {
                        drawLine=true;
                        startX=e.getX();
                        startY=e.getY();
                        before.set(GV.animate);
                        GV.animate=false;
                        mode.set(1);
                    }
                }
                else if(mode.get()==1) {
                    if(!escapeLine){
                        double velX=mouseX-startX;
                        double velY=mouseY-startY;
                        planet.get().x_vel=velX*GV.timePeroid*10;
                        planet.get().y_vel=velY*GV.timePeroid*10;
                    }
                    drawLine=false;
                    GV.animate=before.get();
                    mode.set(0);
                    escapeLine = false;
                }
            }
            if(e.getButton() == MouseButton.SECONDARY){
                if(mode.get() == 1) {
                    escapeLine = true;
                    drawLine = false;
                    mode.set(0);
                    GV.animate=before.get();
                }
            }

        });



        window.addEventHandler(MouseEvent.MOUSE_MOVED, e->{
            mouseX=e.getX();
            mouseY=e.getY();
        });

            /*else if(event.getButton()==MouseButton.SECONDARY) {
                boolean before=GV.animate;
                GV.animate=false;
                Planet planet=OptionWindows.planetOptionWindow(event.getX(), event.getY());
                planet.updateCircle();
                PS.AddPlanet(planet);
                GV.animate=before;
            }*/
        /*EventHandler<ContextMenuEvent> contextMenuEvent=PlanetaryGUI.onContextMenuMainBoxEventHandler();
        window.setOnContextMenuRequested(contextMenuEvent);*/
    }
}
