package main.simulator.planetary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BasicEvents {

    public static EventHandler<ActionEvent> closeEvent=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Main.mainStage.close();
        }
    };

}
