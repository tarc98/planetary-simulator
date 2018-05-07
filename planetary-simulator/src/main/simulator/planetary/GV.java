package main.simulator.planetary;

// Global Variables
public class GV {
    final static double PI = 3.14159265359;
    final static double G = 6.67408e-11; // m^3/(kg*s^2)

    final static double timePeroid = 864; // 0.01 day
    final static double MILLIS = 1000.0/144.0; // 144 FPS

    static double SCALE = 1e-9; // 1e-9 = around 1 Mercury RADIUS per 600px width
    static int animationSpeed = 12; // for( x times simulate)
    static boolean animate = true;

    static double time=0;
}
