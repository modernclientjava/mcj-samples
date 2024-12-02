package org.modernclients.samples;

import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import org.modernclients.Sample;

import java.util.function.Consumer;

import static org.modernclients.Utils.makeMenuItem;

/**
 * @author Jonathan Giles <jonathan@jonathangiles.net>
 */
public class MenuButtonDemo implements Sample {

    @Override
    public void buildDemo(Pane container, Consumer<String> console) {
        MenuButton menuButton = new MenuButton("Choose a meal...");
        menuButton.getItems().addAll(
                makeMenuItem("Burgers", console),
                makeMenuItem("Pizza", console),
                makeMenuItem("Hot Dog", console));

        // because the MenuButton does not have an 'action' area, onAction does nothing
        menuButton.setOnAction(e -> console.accept("MenuButton onAction event"));

        container.getChildren().add(menuButton);
    }
}
