package main.simulator.planetary;

// http://www.physlink.com/Reference/AstroPhysical.cfm

/*
    mass | kg
    radius - adius of planet | m
    RADIUS - distance to ORIGIN | m
    peroid - own planet peroid | s
    PEROID - peroid around ORIGIN | s

    max e+307 , what about dark holes :O

*/


final public class PlanetPreset {
    double mass;
    double radius;
    double RADIUS;
    double PEROID;

    public static final PlanetPreset
            OurSun = new PlanetPreset(10.99e+307,6.96e+108,0,0),              //ORIGIN
            Mercury = new PlanetPreset(3.18e+23,2.43e+6,5.79e+10,7.60e+6),
            Venus = new PlanetPreset(4.88e+24,6.06e+6,1.08e+11,1.94e+7),
            Earth = new PlanetPreset(5.98e+24,6.37e+6,1.496e+11,3.156e+7),
            Mars = new PlanetPreset(6.42e+23,3.37e+6,2.28e+11,5.94e+7),
            Jupiter = new PlanetPreset(1.90e+27,6.99e+7,7.78e+11,3.74e+8),
            Saturn = new PlanetPreset(5.68e+26,5.85e+7,1.43e+12,9.35e+8),
            Uranus = new PlanetPreset(8.68e+25,2.33e+7,2.87e+12,2.64e+9),
            Neptune = new PlanetPreset(1.03e+26,2.21e+7,4.50e+12,5.22e+9),
            Pluto = new PlanetPreset(1.4e+22,1.5e+6,5.91e+12,7.82e+9),
            Empty = new PlanetPreset(0,0,0,0);

    PlanetPreset(double mass, double radius, double RADIUS, double PEROID)
    {
        this.mass = mass;
        this.radius = radius;
        this.RADIUS = RADIUS;
        this.PEROID = PEROID;
    }
}
