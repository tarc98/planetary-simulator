package main.simulator.planetary;

import java.io.InputStreamReader;

public class Test123 {
    public static void main(String[] args) {
        PlanetarySystem PS = new PlanetarySystem();
        PS.AddPlanet(new Planet(PlanetPreset.Mercury.RADIUS, 0, 0, 0, 0, 0, Planet.NAME.OurSun));
        PS.AddPlanet(new Planet(-PlanetPreset.Mercury.RADIUS, 0, 0, 0, 0, 0, Planet.NAME.OurSun));

        long currentTime = 0;
        double timePeroid = 86400; // 1 day

        System.out.println("Days : "+ 0);
        PS.Draw();
        while(!PS.IsColision())
        {
            currentTime+=timePeroid;
            System.out.println("Days : " + ((double)currentTime/3600/24));
            PS.Simulate(timePeroid);
            PS.Draw();


            // press any key to continue
            InputStreamReader reader = new InputStreamReader (System.in);
            int input = 0;
            try {
                input = reader.read();
            }catch (Throwable e){}

        }
    }
}

