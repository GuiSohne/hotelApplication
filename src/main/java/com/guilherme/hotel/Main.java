package com.guilherme.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(FXMLLoader
                .load(getClass()
                        .getResource("/views/frmLogin.fxml")));
        stage.setTitle("Room reservation");
        stage.setScene(scene);
        stage.show();
    }

    void main(){
        launch();
    }
}
