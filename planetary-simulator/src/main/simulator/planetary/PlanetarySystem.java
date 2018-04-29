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
        planetsNext.add(p);
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
        for(Planet i : planetsNext){
            double Fx = 0;
            double Fy = 0;
            double Fz = 0;

            for(Planet j : planets){
                if(i==j)
                    continue;
                // FORCE I<-J

                double d = distance(i.x_pos, i.y_pos, i.z_pos, j.x_pos, j.y_pos, j.z_pos);
                double f=G*i.mass*j.mass/d/d;

                double fx = d/(i.x_pos-j.x_pos)*f;
                double fy = d/(i.y_pos-j.y_pos)*f;
                double fz = d/(i.z_pos-j.z_pos)*f;

                Fx+=fx;
                Fy+=fy;
                Fz+=fz;
            }

            /*
            F = m*a
            a = F/m

            x = x0 + v0*t + a*t*t/2
            x = x0 + v0*t + F*t*t/2/m

            v = v0 + a*t
            v = v0 + F*t/m
            */

            i.x_pos = i.x_pos + i.x_vel*time + Fx*time*time/2/i.mass;
            i.y_pos = i.y_pos + i.y_vel*time + Fy*time*time/2/i.mass;
            i.z_pos = i.z_pos + i.z_vel*time + Fz*time*time/2/i.mass;

            i.x_vel = i.x_vel + Fx*time/i.mass;
            i.y_vel = i.y_vel + Fy*time/i.mass;
            i.z_vel = i.z_vel + Fz*time/i.mass;
        }
        List<Planet> swap = planets;
        planets = planetsNext;
        planetsNext = swap;
    }

    void Draw()
    {
        for(Planet i : planets){
            System.out.println(i.x_pos + ", " + i.y_pos + ", " + i.z_pos);
        }
        System.out.println();
    }
}
