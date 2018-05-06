package main.simulator.planetary;

public class AtomicPlanet {
    Planet planet;
    public AtomicPlanet() {
        planet=null;
    }
    public AtomicPlanet(Planet planet1) {
        planet=planet1;
    }
    public void set(Planet planet1) {
        planet=planet1;
    }
    public Planet get() {
        return planet;
    }
}
