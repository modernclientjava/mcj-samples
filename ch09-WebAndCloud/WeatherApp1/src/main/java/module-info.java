module weatherapp {
    requires javafx.controls;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports org.modernclients.web.model to com.fasterxml.jackson.databind;
    exports org.modernclients.web;
}