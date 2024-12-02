package org.modernclients.raspberrypi.gps.service;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.modernclients.raspberrypi.gps.model.GPSPosition;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class GPSService {

    private static final Logger logger = Logger.getLogger(GPSService.class.getName());

    @Inject
    private GPSPosition gpsPosition;

    private NMEAParser nmea;
    private StringBuilder gpsOutput;
    private SerialPort comPort;

    private final StringProperty line = new SimpleStringProperty();

    @PostConstruct
    private void postConstruct() {
        if (! "monocle".equals(System.getProperty("embedded"))) {
            return;
        }

        nmea = new NMEAParser(gpsPosition);
        gpsOutput = new StringBuilder();

        comPort = getSerialPort("ttyAMA0");
        if (comPort == null) {
            throw new RuntimeException("comPort is null");
        }

        comPort.openPort();
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] newData = new byte[comPort.bytesAvailable()];
                comPort.readBytes(newData, newData.length);
                gpsOutput.append(new String(newData, Charset.defaultCharset())
                        .replaceAll("\n", "")
                        .replaceAll("\r", ""));
                processReading();
            }
        });
    }

    private void processReading() {
        if (gpsOutput == null || gpsOutput.toString().isEmpty()) {
            return;
        }
        
        String reading = gpsOutput.toString().trim();

        if (!reading.contains("$")) {
            return;
        }
        
        String[] split = reading.split("\\$");
        
        for (int i = 0; i < split.length - 1; i++) {
            String line = "$" + split[i];
            gpsOutput.delete(0 , line.length());
            if (line.length() > 1) {
                logger.fine("GPS: " + line);
                Platform.runLater(() -> {
                    nmea.parse(line);
                    this.line.set(line);
                });
            }
            if (i == split.length - 2) {
                gpsOutput.insert(0, "$");
            }
        }
    }

    public final StringProperty lineProperty() {
        return line;
    }

    public void stop() {
        logger.info("Stopping SerialPort");
        if (comPort != null) {
            try {
                comPort.removeDataListener();
                comPort.closePort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static SerialPort getSerialPort(String portName) {
        SerialPort[] comPorts = SerialPort.getCommPorts();
        for (SerialPort port : comPorts) {
            if (portName.equals(port.getSystemPortName())) {
                return port;
            }
        }
        return null;
    }
}
