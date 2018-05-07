package main.simulator.planetary;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlanetarySystem {
    double distance(double ax, double ay, double bx, double by){
        return Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by));
    }

    List<Planet> planets;

    PlanetarySystem(){
        planets = new ArrayList<>();
    }

    void AddPlanet(Planet p){
        planets.add(p);
    }

    boolean IsColision(){
        for(Planet i : planets){
            for(Planet j : planets){
                if(i==j)
                    continue;
                double d = distance(i.x_pos, i.y_pos, j.x_pos, j.y_pos);
                if(d<i.radius+j.radius)
                    return true;
            }
        }
        return false;
    }

    void Simulate(double time){ // seconds
        List<Planet> planetsNext = new ArrayList<>();
        for(Planet i : planets)
            planetsNext.add(new Planet());

        for(int i=0; i<planets.size(); i++){
            double Fx = 0;
            double Fy = 0;

            for(int j=0; j<planets.size(); j++){
                if(i==j)
                    continue;
                //System.out.println(planets.get(i).x_pos+" "+planets.get(i).y_pos+" "+planets.get(i).z_pos+" "+planets.get(j).x_pos+" "+planets.get(j).y_pos+" "+planets.get(j).z_pos);
                double d = distance(planets.get(i).x_pos, planets.get(i).y_pos, planets.get(j).x_pos, planets.get(j).y_pos);
                //System.out.println("d: " + d);
                double f = GV.G*planets.get(i).mass*planets.get(j).mass/d/d;
                //System.out.println("f: " + f);

                double fx = (planets.get(j).x_pos-planets.get(i).x_pos)/d*f;
                double fy = (planets.get(j).y_pos-planets.get(i).y_pos)/d*f;
                //System.out.println("fxyz: "+" "+fx+" "+fy+" "+fz);

                Fx+=fx;
                Fy+=fy;
            }
            //System.out.println("F: "+" "+Fx+" "+Fy+" "+Fz);

            /*
            F = m*a
            a = F/m

            x = x0 + v0*t + a*t*t/-2
            x = x0 + v0*t + F*t*t/2/m

            v = v0 + a*t
            v = v0 + F*t/m
            */

            planetsNext.get(i).x_pos = planets.get(i).x_pos + planets.get(i).x_vel*time + Fx*time*time/2/planets.get(i).mass;
            planetsNext.get(i).y_pos = planets.get(i).y_pos + planets.get(i).y_vel*time + Fy*time*time/2/planets.get(i).mass;

            planetsNext.get(i).x_vel = planets.get(i).x_vel + Fx*time/planets.get(i).mass;
            planetsNext.get(i).y_vel = planets.get(i).y_vel + Fy*time/planets.get(i).mass;
        }

        for(int i=0; i<planetsNext.size(); i++){
            planets.get(i).x_pos = planetsNext.get(i).x_pos;
            planets.get(i).y_pos = planetsNext.get(i).y_pos;
            planets.get(i).x_vel = planetsNext.get(i).x_vel;
            planets.get(i).y_vel = planetsNext.get(i).y_vel;
        }
    }

    void DFS(int index, ArrayList<ArrayList<Integer>> neighbors, boolean [] visited, ArrayList<Integer> component){
        if(!visited[index]) {
            visited[index] = true;
            component.add(index);
            for (int j = 0; j < neighbors.get(index).size(); j++) {
                DFS(neighbors.get(index).get(j), neighbors, visited, component);
            }
        }
    }

    boolean ConnectCollidedPlanets(){ // return true if something was connected
        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>(planets.size());
        for( Planet i : planets)
            neighbors.add(new ArrayList<Integer>());

        boolean isCollision = false;
        for(int i=0; i<planets.size(); i++){
            for(int j=0; j<planets.size(); j++){
                if(i==j)
                    continue;
                double d = distance(planets.get(i).x_pos, planets.get(i).y_pos, planets.get(j).x_pos, planets.get(j).y_pos);
                if(d<planets.get(i).radius+planets.get(j).radius){
                    neighbors.get(i).add(j);
                    isCollision = true;
                }
            }
        }
        if(!isCollision)
            return false;
        /*
        for(int i=0; i<neighbors.size(); i++){
            System.out.print(i+": ");
            for(int j=0; j<neighbors.get(i).size(); j++){
                System.out.print(neighbors.get(i).get(j) +",");
            }
            System.out.println();
        }
        */

        ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>(planets.size());
        boolean[] visited = new boolean[planets.size()]; // default false

        for(int i=0; i<visited.length; i++){
            if(!visited[i]) {
                ArrayList<Integer> component = new ArrayList<Integer>();
                DFS(i, neighbors, visited, component);
                components.add(component);
            }
        }
        /*
        for(int i=0; i<components.size(); i++){
            System.out.print("C" +i+": ");
            for(int j=0; j<components.get(i).size(); j++){
                System.out.print(components.get(i).get(j) +",");
            }
            System.out.println();
        }*/

        boolean[] deletePlanets = new boolean[planets.size()];
        for(ArrayList<Integer> connected : components){
            if(connected.size() > 1) {
                double Px=0, Py=0; // momentum
                double Qx=0, Qy=0; // Qx / Mass = middle of max x = center x of new planet
                double Cr=0, Cg=0, Cb=0; // colors

                double Mass = 0;
                double V = 0;                // volume of sum
                double Radius = 0;
                Color C = Color.color(0,0,0);

                for (int i : connected) {
                    Px += planets.get(i).mass * planets.get(i).x_vel;
                    Py += planets.get(i).mass * planets.get(i).y_vel;

                    Qx += planets.get(i).mass * planets.get(i).x_pos;
                    Qy += planets.get(i).mass * planets.get(i).y_pos;

                    Cr += (planets.get(i).c.getRed() * planets.get(i).V);
                    Cg += (planets.get(i).c.getGreen() * planets.get(i).V);
                    Cb += (planets.get(i).c.getBlue() * planets.get(i).V);

                    Mass += planets.get(i).mass;
                    V += planets.get(i).V;

                    // V=4/3*PI*r*r*r   ->   r=(V*3/4/PI)^(1/3)
                    Radius = Math.pow(V*3.0/4.0/GV.PI, 1.0/3.0);
                    C = Color.color(Cr/V, Cg/V,Cb/V);

                    deletePlanets[i] = true;
                }

                Planet planet=new Planet(Qx/Mass, Qy/Mass, Px/Mass, Py/Mass, Mass, Radius, "", C);
                planet.addCircle();
                if(MainBox.showSpeed) planet.setSpeedLabel();
                planets.add(planet);
            }
        }

        for(int i=deletePlanets.length-1; i>=0; i--){
            //System.out.print(deletePlanets[i]);
            if(deletePlanets[i]) {
                planets.get(i).removeCircle();
                planets.get(i).removeSpeedLabel();
                planets.remove(i);
            }
        }
        //System.out.println();

        return true;
    }

    void Draw(){
        for(Planet i : planets){
            System.out.println("x="+i.x_pos + ", y=" + i.y_pos + "   Vx="+i.x_vel + ", Vy=" + i.y_vel);
        }
        System.out.println();
    }
    public void setLabels() {
        for(Planet p : planets) {
            p.setSpeedLabel();
        }
    }

    public void updateLabels() {
        for(Planet p : planets) {
            p.updateSpeedLabel();
        }
    }

    public void removeLabels() {
        for(Planet p : planets) {
            p.removeSpeedLabel();
        }
    }
}
