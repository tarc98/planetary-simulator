package main.simulator.planetary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class GlobalEvents {

    public static void addEvents(Scene scene) {
        scene.addEventHandler(KeyEvent.ANY, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (GV.animate)
                    GV.animate = false;
                else
                    GV.animate = true;
            }
        });
    }

    public static EventHandler<ActionEvent> closeEvent=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Main.mainStage.close();
        }
    };
}
