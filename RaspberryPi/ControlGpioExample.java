import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;

public class ControlGpioExample {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J v2.8.0--> GPIO Control Example ... started.");

        // Initialize Pi4J context
        Context pi4j = Pi4J.newAutoContext();

        // Create Digital Output config for GPIO pin 1 (GPIO_01)
        DigitalOutputConfig outputConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("My LED")
                .address(1) // GPIO 1 (WiringPi numbering)
                .shutdown(DigitalState.LOW) // Pin state when program terminates
                .initial(DigitalState.HIGH) // Initial pin state when provisioned
                .build();

        // Create Digital Output instance
        DigitalOutput led = pi4j.create(outputConfig);

        System.out.println("--> GPIO state should be: ON");
        Thread.sleep(5000);

        System.out.println("--> GPIO state should be: OFF");
        led.low();
        Thread.sleep(5000);

        System.out.println("--> GPIO state should be: ON (toggle)");
        led.toggle();
        Thread.sleep(5000);

        System.out.println("--> GPIO state should be: OFF (toggle)");
        led.toggle();
        Thread.sleep(5000);

        System.out.println("--> GPIO pulse for 1 second");
        led.pulse(1000, java.util.concurrent.TimeUnit.MILLISECONDS, DigitalState.LOW);

        // Shutdown Pi4J context and release resources
        pi4j.shutdown();

        System.out.println("Exiting ControlGpioExample");
    }
}
