package org.modernclients.samples;

import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import org.modernclients.Sample;

import java.util.function.Consumer;

/**
 * @author Jonathan Giles <jonathan@jonathangiles.net>
 */
public class HTMLEditorDemo implements Sample {

    @Override
    public void buildDemo(Pane container, Consumer<String> console) {
        HTMLEditor htmlEditor = new HTMLEditor();

        container.getChildren().add(htmlEditor);
    }
}
