package main.simulator.planetary;

import java.util.List;
import java.util.ArrayList;

public class PlanetarySystem {
    List<Planet> planets;
    List<Planet> planetsNext;
    final static double G = 6.67408e-11; // m^3/(kg*s^2)

    PlanetarySystem()
    {
        planets = new ArrayList<>();
        planetsNext = new ArrayList<>();
    }

    void AddPlanet(Planet p)
    {
        planets.add(p);
        planetsNext.add(new Planet());
    }

    double distance(double ax, double ay, double az, double bx, double by, double bz){
        return Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by)+(az-bz)*(az-bz));
    }

    boolean IsColision(){
        for(Planet i : planets){
            for(Planet j : planets){
                if(i==j)
                    continue;
                double d = distance(i.x_pos, i.y_pos, i.z_pos, j.x_pos, j.y_pos, j.z_pos);
                if(d<i.radius+j.radius)
                    return true;
            }
        }
        return false;
    }

    void Simulate(double time){ // seconds
        for(int i=0; i<planets.size(); i++){
            double Fx = 0;
            double Fy = 0;
            double Fz = 0;

            for(int j=0; j<planets.size(); j++){
                if(i==j)
                    continue;
                //System.out.println(planets.get(i).x_pos+" "+planets.get(i).y_pos+" "+planets.get(i).z_pos+" "+planets.get(j).x_pos+" "+planets.get(j).y_pos+" "+planets.get(j).z_pos);
                double d = distance(planets.get(i).x_pos, planets.get(i).y_pos, planets.get(i).z_pos, planets.get(j).x_pos, planets.get(j).y_pos, planets.get(j).z_pos);
                //System.out.println("d: " + d);
                double f=G*planets.get(i).mass*planets.get(j).mass/d/d;
                //System.out.println("f: " + f);

                double fx = (planets.get(j).x_pos-planets.get(i).x_pos)/d*f;
                double fy = (planets.get(j).y_pos-planets.get(i).y_pos)/d*f;
                double fz = (planets.get(j).z_pos-planets.get(i).z_pos)/d*f;
                //System.out.println("fxyz: "+" "+fx+" "+fy+" "+fz);

                Fx+=fx;
                Fy+=fy;
                Fz+=fz;
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
            planetsNext.get(i).z_pos = planets.get(i).z_pos + planets.get(i).z_vel*time + Fz*time*time/2/planets.get(i).mass;

            planetsNext.get(i).x_vel = planets.get(i).x_vel + Fx*time/planets.get(i).mass;
            planetsNext.get(i).y_vel = planets.get(i).y_vel + Fy*time/planets.get(i).mass;
            planetsNext.get(i).z_vel = planets.get(i).z_vel + Fz*time/planets.get(i).mass;
        }

        for(int i=0; i<planetsNext.size(); i++)
        {
            planets.get(i).x_pos = planetsNext.get(i).x_pos;
            planets.get(i).y_pos = planetsNext.get(i).y_pos;
            planets.get(i).z_pos = planetsNext.get(i).z_pos;
            planets.get(i).x_vel = planetsNext.get(i).x_vel;
            planets.get(i).y_vel = planetsNext.get(i).y_vel;
            planets.get(i).z_vel = planetsNext.get(i).z_vel;
        }
        /*
        List<Planet> swap = planets;
        planets = planetsNext;
        planetsNext = swap;*/
    }

    void Draw()
    {
        for(Planet i : planets){
            System.out.println("x="+i.x_pos + ", y=" + i.y_pos + ", z=" + i.z_pos +"   Vx="+i.x_vel + ", Vy=" + i.y_vel + ", Vz=" + i.z_vel);
        }
        System.out.println();
    }
}
