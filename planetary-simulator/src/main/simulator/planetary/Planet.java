package main.simulator.planetary;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;


public class Planet {
    double x_pos;
    double y_pos;
    double z_pos;

    double x_vel;
    double y_vel;
    double z_vel;

    double mass;
    double radius;

    String name;
    Color c;

    Circle planetCircle;

    double V;
    double d; // destiny of planet

    void SetProperties()
    {
        V = 4.0/3.0*GV.PI*radius*radius*radius;
        d = mass/V;
    }

    enum NAME{
        OurSun, Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, Empty
    }
    PlanetPreset setPlanetRef(NAME presetName){
        PlanetPreset ref;

        switch (presetName){
            case OurSun:
                ref = PlanetPreset.OurSun;
                break;
            case Mercury:
                ref = PlanetPreset.Mercury;
                break;
            case Venus:
                ref = PlanetPreset.Venus;
                break;
            case Earth:
                ref = PlanetPreset.Earth;
                break;
            case Mars:
                ref = PlanetPreset.Mars;
                break;
            case Jupiter:
                ref = PlanetPreset.Jupiter;
                break;
            case Saturn:
                ref = PlanetPreset.Saturn;
                break;
            case Uranus:
                ref = PlanetPreset.Uranus;
                break;
            case Neptune:
                ref = PlanetPreset.Neptune;
                break;
            case Pluto:
                ref = PlanetPreset.Pluto;
                break;
            default:
                ref = PlanetPreset.Empty;
        }
        return ref;
    }

    Planet(){
        x_pos = 0;
        y_pos = 0;
        z_pos = 0;

        x_vel = 0;
        y_vel = 0;
        z_vel = 0;

        mass = 0;
        radius = 0;

        name = "";
        c = Color.color(0,0,0);

        SetProperties();
    }
    Planet(double x_pos, double y_pos, double z_pos, double x_vel, double y_vel, double z_vel, double mass, double radius){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;

        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.z_vel = z_vel;

        this.mass = mass;
        this.radius = radius;

        name = "";
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        c = Color.color(r,g,b);

        SetProperties();
    }

    Planet(double x_pos, double y_pos, double z_pos, double x_vel, double y_vel, double z_vel, double mass, double radius, String name, Color c){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;

        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.z_vel = z_vel;

        this.mass = mass;
        this.radius = radius;

        this.name = name;
        this.c = c;

        SetProperties();
    }

    Planet(double x_pos, double y_pos, double z_pos, double x_vel, double y_vel, double z_vel, NAME presetName){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;

        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.z_vel = z_vel;

        PlanetPreset ref = setPlanetRef(presetName);
        mass = ref.mass;
        radius = ref.radius;

        name = "";
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        c = Color.color(r,g,b);

        SetProperties();
    }

    public Planet setPlanetCircle(Circle circle) {
        this.planetCircle=circle;
        return this;
    }

    public Circle getPlanetCircle() {
        return this.planetCircle;
    }

    public Color getColor() {
        return this.c;
    }

    public void updateCircle() {
        Main.mainBox.getChildren().remove(planetCircle);
        planetCircle=new Circle(x_pos*GV.SCALE, y_pos*GV.SCALE, radius*GV.SCALE*10, c);
        Main.mainBox.getChildren().add(planetCircle);
    }

    public void removeCircle() {
        Main.mainBox.getChildren().remove(planetCircle);
    }


    //                  NOT FINISHED
    Planet(double Xy_pos, double Zxy_pos, double xy_cross, boolean toCross, NAME presetName){ // only perpendicular velocity vectors to origin
        /*
        Xy(x-asix into y axis), Zxy(z-axis into xy-field), angle_vel right s
        xy_cross - angle where orbit crosses xy field
        toCross - true if we go from center of planet to cross
        https://commons.wikimedia.org/wiki/File:Polar-coordinates-3D.svg

        https://math.stackexchange.com/questions/2375102/parametric-equation-of-a-circle-in-3d-given-center-and-two-points-on-the-circle

        */

        /*
        wyliczyć punkt zaczenienia wektora
        wyliczyć kierunek wektora
        wyliczyć szybkość z PEROID i RADIUS
        wyliczyć składowe wektora
        */

        PlanetPreset ref = setPlanetRef(presetName);
        double RADIUS = ref.RADIUS;
        x_pos = RADIUS * Math.sin(Xy_pos) * Math.sin(Zxy_pos);
        y_pos = RADIUS * Math.cos(Xy_pos) * Math.sin(Zxy_pos);
        z_pos = RADIUS * Math.cos(Zxy_pos);

        double x_cross = RADIUS * Math.sin(xy_cross);
        double y_cross = RADIUS * Math.cos(xy_cross);
        double z_cross = 0;
        //                                                          CHECK IF POINTS ARE NOT ON A DIAMETER ON CIRCLE OR IT IS NOT SAME POINT

        // ???


    }

}
