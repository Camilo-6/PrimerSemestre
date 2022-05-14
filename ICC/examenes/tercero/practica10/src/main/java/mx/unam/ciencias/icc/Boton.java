package mx.unam.ciencias.icc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Boton extends Application  {


    private int numero;
    private Label veces = new Label("");

   public void start(Stage escenario) throws Exception {

        Button boton = new Button();
        boton.setText("Click");
        boton.setOnAction(e -> {
		    veces.setText(Integer.toString(numero));
		    contar();
	    });

        HBox cajita = new HBox();
	    cajita.getChildren().addAll(boton, veces);
	    Scene escena = new Scene(cajita, 75, 50);
	    escenario.setScene(escena);
	    escenario.show();

    }

    public void contar() {
	    numero++;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
