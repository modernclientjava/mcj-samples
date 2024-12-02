package org.modernclients.javafx3d;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.DirectionalLight;
import javafx.scene.Group;
import javafx.scene.LightBase;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SpotLight;
import javafx.scene.SubScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

public class LightDemo extends Application {
    private final Model model;

    public LightDemo() {
        model = new Model();
    }

    @Override
    public void start(Stage stage) {
        View view = new View(model);
        stage.setTitle("Light Example");
        stage.setScene(view.scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Model {
        private final DoubleProperty redLightX = new SimpleDoubleProperty(this, "redLightX", 20.0d);
        private final DoubleProperty redLightY = new SimpleDoubleProperty(this, "redLightY", -15.0d);
        private final DoubleProperty redLightZ = new SimpleDoubleProperty(this, "redLightZ", -20.0d);
        private final DoubleProperty blueLightX = new SimpleDoubleProperty(this, "blueLightX", 15.0d);
        private final DoubleProperty blueLightY = new SimpleDoubleProperty(this, "blueLightY", -15.0d);
        private final DoubleProperty blueLightZ = new SimpleDoubleProperty(this, "blueLightZ", -5.0d);
        private final DoubleProperty greenLightX = new SimpleDoubleProperty(this, "greenLightX", 30.0d);
        private final DoubleProperty greenLightY = new SimpleDoubleProperty(this, "greenLightY", -15.0d);
        private final DoubleProperty greenLightZ = new SimpleDoubleProperty(this, "greenLightZ", 305.0d);
        private final DoubleProperty yellowLightX = new SimpleDoubleProperty(this, "yellowLightX", 30.0d);
        private final DoubleProperty yellowLightY = new SimpleDoubleProperty(this, "yellowLightY", -15.0d);
        private final DoubleProperty yellowLightZ = new SimpleDoubleProperty(this, "yellowLightZ", 305.0d);

        public DoubleProperty redLightXProperty() {
            return redLightX;
        }

        public DoubleProperty redLightYProperty() {
            return redLightY;
        }

        public DoubleProperty redLightZProperty() {
            return redLightZ;
        }

        public DoubleProperty blueLightXProperty() {
            return blueLightX;
        }

        public DoubleProperty blueLightYProperty() {
            return blueLightY;
        }

        public DoubleProperty blueLightZProperty() {
            return blueLightZ;
        }

        public DoubleProperty greenLightXProperty() {
            return greenLightX;
        }

        public DoubleProperty greenLightYProperty() {
            return greenLightY;
        }

        public DoubleProperty greenLightZProperty() {
            return greenLightZ;
        }

        public DoubleProperty yellowLightXProperty() {
            return yellowLightX;
        }

        public DoubleProperty yellowLightYProperty() {
            return yellowLightY;
        }

        public DoubleProperty yellowLightZProperty() {
            return yellowLightZ;
        }
    }

    private static class View {
        public Scene scene;

        private final Box box1;
        private final Box box2;
        private final Box box3;
        private final PerspectiveCamera camera;
        private final PointLight redLight;
        private final PointLight blueLight;
        private final SpotLight greenLight;
        private final DirectionalLight yellowLight;

        private View(Model model) {
            box1 = new Box(10, 10, 10);
            box1.setId("Box1");
            box1.getTransforms().add(new Translate(-15, 0, 0));
            box2 = new Box(10, 10, 10);
            box2.setId("Box2");
            box3 = new Box(10, 10, 10);
            box3.setId("Box3");
            box3.getTransforms().add(new Translate(15, 0, 0));

            camera = new PerspectiveCamera(true);

            Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
            Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);
            Rotate rotateZ = new Rotate(-20, Rotate.Z_AXIS);
            Translate translateZ = new Translate(0, 0, -60);

            camera.getTransforms().addAll(rotateX, rotateY, rotateZ, translateZ);

            redLight = new PointLight(Color.RED);
            redLight.translateXProperty().bind(model.redLightXProperty());
            redLight.translateYProperty().bind(model.redLightYProperty());
            redLight.translateZProperty().bind(model.redLightZProperty());

            blueLight = new PointLight(Color.BLUE);
            blueLight.translateXProperty().bind(model.blueLightXProperty());
            blueLight.translateYProperty().bind(model.blueLightYProperty());
            blueLight.translateZProperty().bind(model.blueLightZProperty());

            greenLight = new SpotLight(Color.GREEN);
            greenLight.translateXProperty().bind(model.greenLightXProperty());
            greenLight.translateYProperty().bind(model.blueLightYProperty());
            greenLight.translateZProperty().bind(model.blueLightZProperty());

            yellowLight = new DirectionalLight(Color.YELLOW);
            yellowLight.translateXProperty().bind(model.yellowLightXProperty());
            yellowLight.translateYProperty().bind(model.blueLightYProperty());
            yellowLight.translateZProperty().bind(model.blueLightZProperty());

            Group group = new Group(new Group(box1, box2, box3),
                    camera, redLight, blueLight, greenLight, yellowLight);
            SubScene subScene = new SubScene(group, 640, 400, true, SceneAntialiasing.BALANCED);
            subScene.setCamera(camera);

            // Red Light
            Tab redTab = createTab("Red Light", redLight);

            CheckBox redLightOn = createCheckBox(redLight);
            HBox hbox1 = createXYZHBox(model.redLightX, model.redLightY, model.redLightZ);
            HBox hbox2 = new HBox(10, createScopeToggles(box1), createScopeToggles(box2), createScopeToggles(box3));
            hbox2.setPadding(new Insets(10, 10, 10, 10));
            hbox2.setAlignment(Pos.CENTER);

            VBox redControlPanel = new VBox(10, redLightOn, hbox1, hbox2);
            redControlPanel.setPadding(new Insets(10, 10, 10, 10));
            redControlPanel.setAlignment(Pos.CENTER);
            redTab.setContent(redControlPanel);

            // Blue Light
            Tab blueTab = createTab("Blue Light", blueLight);

            CheckBox blueLightOn = createCheckBox(blueLight);
            HBox hbox3 = createXYZHBox(model.blueLightX, model.blueLightY, model.blueLightZ);
            HBox hbox4 = addLightControls(blueLight);

            VBox blueControlPanel = new VBox(10, blueLightOn, hbox3, hbox4);
            blueControlPanel.setPadding(new Insets(10, 10, 10, 10));
            blueControlPanel.setAlignment(Pos.CENTER);
            blueTab.setContent(blueControlPanel);

            // Green Light
            Tab greenTab = createTab("Green Light", greenLight);

            CheckBox greenLightOn = createCheckBox(greenLight);
            HBox hbox5 = createXYZHBox(model.greenLightX, model.greenLightY, model.greenLightZ);
            HBox hBox6 = addSpotLightControls(greenLight);

            VBox greenControlPanel = new VBox(10, greenLightOn, hbox5, hBox6);
            greenControlPanel.setPadding(new Insets(10, 10, 10, 10));
            greenControlPanel.setAlignment(Pos.CENTER);
            greenTab.setContent(greenControlPanel);

            // Yellow Light
            Tab yellowTab = createTab("Yellow Light", yellowLight);

            CheckBox yellowLightOn = createCheckBox(yellowLight);
            HBox hbox7 = createXYZHBox(model.yellowLightX, model.yellowLightY, model.yellowLightZ);
            HBox hBox8 = addDirectionalLightControls(yellowLight);

            VBox yellowControlPanel = new VBox(10, yellowLightOn, hbox7, hBox8);
            yellowControlPanel.setPadding(new Insets(10, 10, 10, 10));
            yellowControlPanel.setAlignment(Pos.CENTER);
            yellowTab.setContent(yellowControlPanel);

            TabPane tabPane = new TabPane(redTab, blueTab, greenTab, yellowTab);
            BorderPane borderPane = new BorderPane(subScene, null, null, tabPane, null);
            scene = new Scene(borderPane);
        }

        private Tab createTab(String title, LightBase light) {
            Tab tab = new Tab(title);
            tab.setClosable(false);
            Rectangle red = new Rectangle(10, 10);
            red.fillProperty().bind(Bindings.when(light.lightOnProperty()).then(light.getColor()).otherwise(Color.DARKGREY));
            tab.setGraphic(red);
            return tab;
        }

        private CheckBox createCheckBox(LightBase light) {
            CheckBox lightOnCheckBox = new CheckBox("Light On/Off");
            lightOnCheckBox.setSelected(true);
            light.lightOnProperty().bind(lightOnCheckBox.selectedProperty());
            return lightOnCheckBox;
        }

        private HBox createXYZHBox(DoubleProperty xProperty, DoubleProperty yProperty, DoubleProperty zProperty) {
            Slider lightXSlider = createSlider(-40, 40,20);
            Slider lightYSlider = createSlider(-40, 40, -20);
            Slider lightZSlider = createSlider(-40, 40, -20);
            lightXSlider.valueProperty().bindBidirectional(xProperty);
            lightYSlider.valueProperty().bindBidirectional(yProperty);
            lightZSlider.valueProperty().bindBidirectional(zProperty);

            HBox hBox = new HBox(10, new Label("x:"), lightXSlider,
                    new Label("y:"), lightYSlider,
                    new Label("z:"), lightZSlider);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        // since JavaFX 13 -->
        private Pane createScopeToggles(Node node) {
            RadioButton none = new RadioButton("none");
            none.setOnAction(a -> {
                redLight.getScope().remove(node);
                redLight.getExclusionScope().remove(node);
            });

            RadioButton scoped = new RadioButton("scoped");
            scoped.setOnAction(a -> redLight.getScope().add(node));

            RadioButton excluded = new RadioButton("excluded");
            excluded.setOnAction(a -> redLight.getExclusionScope().add(node));
            none.setSelected(true);

            ToggleGroup tg = new ToggleGroup();
            tg.getToggles().addAll(none, scoped, excluded);
            var vBox = new VBox(5, none, scoped, excluded);
            return new HBox(10, new Label(node.getId()), vBox);
        }

        // since JavaFX 16 -->
        private HBox addLightControls(PointLight light) {
            VBox range = createSliderControl("range", light.maxRangeProperty(), 0, 100, light.getMaxRange());
            VBox c = createSliderControl("constant", light.constantAttenuationProperty(), -1, 1, light.getConstantAttenuation());
            VBox lc = createSliderControl("linear", light.linearAttenuationProperty(), -0.1, 0.1, light.getLinearAttenuation());
            VBox qc = createSliderControl("quadratic", light.quadraticAttenuationProperty(), -0.01, 0.01, light.getQuadraticAttenuation());
            HBox hBox = new HBox(10, range, c, lc, qc);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        // since JavaFX 17 -->
        private HBox addSpotLightControls(SpotLight light) {
            VBox innerAngle = createSliderControl("innerAngle", light.innerAngleProperty(), 0, 180, light.getInnerAngle());
            VBox outerAngle = createSliderControl("outerAngle", light.outerAngleProperty(), 0, 180, light.getOuterAngle());
            VBox falloff = createSliderControl("falloff", light.falloffProperty(), -5, 5, light.getFalloff());
            HBox hBox = new HBox(10, innerAngle, outerAngle, falloff);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        // since JavaFX 18 -->
        private HBox addDirectionalLightControls(DirectionalLight light) {
            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(createDirectionControls(light.getTransforms(), light.directionProperty()));
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private List<Node> createDirectionControls(ObservableList<Transform> transforms, ObjectProperty<Point3D> directionProperty) {
            Rotate transX = new Rotate(0, Rotate.X_AXIS);
            Rotate transY = new Rotate(0, Rotate.Y_AXIS);
            Rotate transZ = new Rotate(0, Rotate.Z_AXIS);
            transforms.addAll(transX, transY, transZ);
            VBox rotX = createSliderControl("rot x", transX.angleProperty(), -180, 180, 0);
            VBox rotY = createSliderControl("rot y", transY.angleProperty(), -180, 180, 0);
            VBox rotZ = createSliderControl("rot z", transZ.angleProperty(), -180, 180, 0);

            Slider sliderX = createSlider(-5, 5, directionProperty.get().getX());
            Slider sliderY = createSlider(-5, 5, directionProperty.get().getY());
            Slider sliderZ = createSlider(-5, 5, directionProperty.get().getZ());
            directionProperty.bind(Bindings.createObjectBinding(() ->
                            new Point3D(sliderX.getValue(), sliderY.getValue(), sliderZ.getValue()),
                    sliderX.valueProperty(), sliderY.valueProperty(), sliderZ.valueProperty()));
            VBox dirX = createSliderControl("dir x", sliderX);
            VBox dirY = createSliderControl("dir y", sliderY);
            VBox dirZ = createSliderControl("dir z", sliderZ);

            return List.of(rotX, rotY, rotZ, dirX, dirY, dirZ);
        }

        private VBox createSliderControl(String name, DoubleProperty property, double min, double max, double start) {
            var slider = createSlider(min, max, start);
            property.bind(slider.valueProperty());
            return createSliderControl(name, slider);
        }

        private VBox createSliderControl(String name, Slider slider) {
            TextField tf = createTextField(slider);
            return new VBox(5, new Label(name), new HBox(slider, tf));
        }

        private TextField createTextField(Slider slider) {
            TextField tf = new TextField();
            tf.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());
            tf.setMaxWidth(50);
            return tf;
        }

        private Slider createSlider(double min, double max, double start) {
            Slider slider = new Slider(min, max, start);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);
            return slider;
        }

    }
}
