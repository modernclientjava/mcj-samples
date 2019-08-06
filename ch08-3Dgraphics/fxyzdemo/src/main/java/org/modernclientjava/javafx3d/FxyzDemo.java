package org.modernclientjava.javafx3d;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import org.fxyz3d.shapes.primitives.SpringMesh;

public class FxyzDemo extends Application {

   @Override
    public void start(Stage primaryStage) {
              SpringMesh spring = new SpringMesh(10, 2, 2, 8 * 2 * Math.PI, 200, 100, 0, 0);
        spring.setCullFace(CullFace.NONE);
        spring.setTextureModeVertices3D(1530, p -> p.f);
 
        Scene scene = new Scene(new Group(spring), 600, 400, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BISQUE);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXyz3D Sample");
        primaryStage.show();
    }
}
