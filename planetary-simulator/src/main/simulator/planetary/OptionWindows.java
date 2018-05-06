package main.simulator.planetary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OptionWindows {



    public static Planet planetOptionWindow(double x, double y) {

        Planet returnPlanet=new Planet(x/GV.SCALE, y/GV.SCALE, 0, 0, Planet.NAME.OurSun);

        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        BorderPane main=new BorderPane();
        GridPane gridPane=new GridPane();
        VBox vbox=new VBox();
        Scene scene=new Scene(main, 400, 300);

        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label massL=new Label("Mass (in Sun masses): ");
        Label radiusL=new Label("Radius (in Sun radiuses): ");
        Button create=new Button("Create Planet");

        TextField massInput=new TextField();
        massInput.setText("1");
        TextField radiusInput=new TextField();
        radiusInput.setText("1");

        gridPane.setConstraints(massL, 0, 0);
        gridPane.setConstraints(massInput, 1, 0);
        gridPane.setConstraints(radiusL, 0, 1);
        gridPane.setConstraints(radiusInput, 1, 1);



        gridPane.getChildren().addAll(massL, radiusL, massInput, radiusInput);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().add(create);
        vbox.setPadding(new Insets(10,10,10,10));
        main.setTop(gridPane);
        main.setBottom(vbox);

        create.setOnAction(e->{
            String m=massInput.getText();
            String r=radiusInput.getText();
            double mass=Double.parseDouble(m);
            double radius=Double.parseDouble(r);

            returnPlanet.mass=mass*PlanetPreset.OurSun.mass;
            returnPlanet.radius=radius*PlanetPreset.OurSun.radius;

            stage.close();
        });


        stage.setScene(scene);
        stage.show();

        return returnPlanet;
    }
}
