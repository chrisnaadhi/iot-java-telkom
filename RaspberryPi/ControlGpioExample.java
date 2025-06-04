import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class ControlGpioExample {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J v3--> GPIO Control Example ... started.");

        // Initialize Pi4J with an auto context
        Context pi4j = Pi4J.newAutoContext();

        // Configure digital output on GPIO 18 (BCM numbering)
        DigitalOutputConfig config = DigitalOutput.newConfigBuilder(pi4j)
                .id("my-led")
                .name("MyLED")
                .address(18) // BCM GPIO pin number (GPIO18 = physical pin 12)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.HIGH)
                .provider("pigpio-digital-output") // or "linuxfs-digital-output" if pigpio not available
                .build();

        DigitalOutput pin = pi4j.create(config);

        System.out.println("--> GPIO state should be: ON");
        Thread.sleep(5000);

        pin.low();
        System.out.println("--> GPIO state should be: OFF");
        Thread.sleep(5000);

        pin.toggle();
        System.out.println("--> GPIO state should be: ON");
        Thread.sleep(5000);

        pin.toggle();
        System.out.println("--> GPIO state should be: OFF");
        Thread.sleep(5000);

        System.out.println("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000); // pulse for 1000ms

        // Clean shutdown
        pi4j.shutdown();
        System.out.println("Exiting ControlGpioExample");
    }
}
