package main.simulator.planetary;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;
import main.simulator.planetary.PlanetaryExceptions.MenuException;

import java.util.ArrayList;

public class PlanetaryMenuBar extends MenuBar {
    ArrayList<Menu> menus;

    public PlanetaryMenuBar() {
        menus=new ArrayList<>();
    }

    public PlanetaryMenuBar addMenu(String name) throws MenuException {
        for(Menu m : menus) {
            if(m.getText()==name) throw new MenuException("There cannot be two menus with the same name");
        }
        Menu temp=new Menu(name);
        menus.add(temp);
        this.getMenus().add(temp);
        return this;
    }
    public boolean removeMenu(String name) {
        for(Menu m : menus) {
            if(m.getText()==name) {
                this.getMenus().remove(m);
                menus.remove(m);
                return true;
            }
        }
        return false;
    }
    public PlanetaryMenuBar addMenuItem(String menuName, String menuItemName, EventHandler eventHandler) throws MenuException {
        for(Menu m : menus) {
            if(m.getText()==menuName) {
                for(MenuItem mi : m.getItems()) {
                    if(mi.getText()==menuItemName) {
                        throw new MenuException("There cannot be two menu items with the same name in on menu");
                    }
                }
                MenuItem temp=new MenuItem(menuItemName);
                temp.setOnAction(eventHandler);
                m.getItems().add(temp);
                return this;
            }
        }
        throw new MenuException("There is no menu called " + menuName);
    }
    public PlanetaryMenuBar addSeparator(String menuName) {
        for(Menu m : this.getMenus()) {
            if(m.getText()==menuName) {
                m.getItems().add(new SeparatorMenuItem());
            }
        }
        return this;
    }
}
