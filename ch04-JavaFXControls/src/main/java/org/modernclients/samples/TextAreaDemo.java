package org.modernclients.samples;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import org.modernclients.Sample;

import java.util.function.Consumer;

/**
 * @author Jonathan Giles <jonathan@jonathangiles.net>
 */
public class TextAreaDemo implements Sample {

    @Override
    public void buildDemo(Pane container, Consumer<String> console) {
        TextArea textArea = new TextArea();
        textArea.setPromptText("Enter description here");

        // we can also observe input in real time
        textArea.textProperty().addListener((o, oldValue, newValue) -> console.accept("current text input is " + newValue));

        container.getChildren().add(textArea);
    }
}
