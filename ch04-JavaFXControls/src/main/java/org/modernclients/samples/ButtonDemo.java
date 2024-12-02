package org.modernclients.samples;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.modernclients.Sample;

import java.util.function.Consumer;

/**
 * @author Jonathan Giles <jonathan@jonathangiles.net>
 */
public class ButtonDemo implements Sample {

    @Override
    public void buildDemo(Pane container, Consumer<String> console) {
        Button button = new Button("Click Me!");
        button.setOnAction(event -> console.accept("Button was clicked"));

        container.getChildren().addAll(button);
    }
}
