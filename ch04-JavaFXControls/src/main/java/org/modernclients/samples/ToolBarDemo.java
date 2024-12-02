package org.modernclients.samples;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.modernclients.Sample;

import java.util.function.Consumer;

/**
 * @author Jonathan Giles <jonathan@jonathangiles.net>
 */
public class ToolBarDemo implements Sample {

    @Override
    public void buildDemo(Pane container, Consumer<String> console) {
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(
            new Button("New"),
            new Button("Open"),
            new Button("Save"),
            new Separator(),
            new Button("Clean"),
            new Button("Compile"),
            new Button("Run"),
            new Separator(),
            new Button("Debug"),
            new Button("Profile")
        );

        container.getChildren().add(toolBar);
    }
}
