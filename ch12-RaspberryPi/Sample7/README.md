## Home-made in-car navigation system

JavaFX 23 interface for the Raspberry Pi 5 and a GPS connected to the UART

Requires Java 21 and JavaFX 23

### From a Desktop machine 

Clone the sample. Run:
 
     ./gradlew runRemoteEmbedded

### Remote (SSH) or locally

Once the project is deployed, run:

    cd /home/pi/ModernClients/ch12-RaspberryPi/embeddedMaps/dist
    sudo java -p /opt/armv6hf-sdk/lib:. -Dembedded=monocle -Dglass.platform=Monocle -cp '*' -m org.modernclients.raspberrypi.gps/org.modernclients.raspberrypi.gps.MainApp
