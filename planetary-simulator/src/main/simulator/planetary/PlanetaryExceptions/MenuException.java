package main.simulator.planetary.PlanetaryExceptions;

import java.io.IOException;

public class MenuException extends Exception {
    public MenuException() {
    }

    public MenuException(String s) {
        super(s);
    }

    public MenuException(String s, Throwable t) {
        super(s, t);
    }

    public MenuException(Throwable t) {
        super(t);
    }

}
