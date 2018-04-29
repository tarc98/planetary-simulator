package main.simulator.planetary;

import java.io.InputStreamReader;

public class Test123 {
    public static void main(String[] args) {
        PlanetarySystem PS = new PlanetarySystem();
        PS.AddPlanet(new Planet(PlanetPreset.Mercury.RADIUS, 0, 0, 0, 0, 0, Planet.NAME.Earth));
        PS.AddPlanet(new Planet(-PlanetPreset.Mercury.RADIUS, 0, 0, 0, 0, 0, Planet.NAME.Earth));

        long currentTime = 0;
        double timePeroid = 86400; // 1 day

        System.out.println(timePeroid + ":");
        PS.Draw();
        while(!PS.IsColision())
        {
            System.out.println(currentTime + ":");
            PS.Simulate(timePeroid);
            PS.Draw();
            currentTime+=timePeroid;


            // press any key to continue
            InputStreamReader reader = new InputStreamReader (System.in);
            int input = 0;
            try {
                input = reader.read();
            }catch (Throwable e){}

        }
    }
}
