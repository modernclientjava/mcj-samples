module weatherapp {
    requires javafx.controls;
    requires com.gluonhq.connect;

    exports org.modernclients.web.model to com.gluonhq.connect;
    exports org.modernclients.web;
}