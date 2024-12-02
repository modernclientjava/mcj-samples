module weatherapp {
    requires javafx.controls;
    requires com.gluonhq.cloudlink.client;
    requires com.gluonhq.attach.storage;

    exports org.modernclients.web.model to com.gluonhq.connect;
    exports org.modernclients.web;
}