/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_mp3_player_pk;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.TRANSPARENT;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PK YADAV
 */
public class JavaFX_Mp3_Player_PK extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    private static class Delta {

        double x, y;
    }

        final Delta dragDelta = new Delta();
        final BooleanProperty inDrag = new SimpleBooleanProperty(false);

    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            dragDelta.x = stage.getX() - event.getScreenX();
            dragDelta.y = stage.getY() - event.getScreenY();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        }
    });

    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX() + dragDelta.x);
            stage.setY(event.getScreenY() + dragDelta.y);
            stage.getWidth();
            stage.getHeight();
            stage.getX();
            stage.getY();
            inDrag.set(true);

        }
    });

        Scene scene = new Scene(root, TRANSPARENT);

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
