import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class ControlGpioExample {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J v2--> GPIO Control Example ... started.");

        // Create Pi4J context (auto detect platform)
        Context pi4j = Pi4J.newAutoContext();

        // Create Digital Output configuration for BCM GPIO 18
        DigitalOutputConfig outputConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("my-led")
                .name("My LED")
                .address(18) // BCM pin 18
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.HIGH)
                .build();

        // Create Digital Output instance
        DigitalOutput led = pi4j.create(outputConfig);

        System.out.println("LED ON");
        Thread.sleep(5000);

        led.low();
        System.out.println("LED OFF");
        Thread.sleep(5000);

        led.toggle();
        System.out.println("LED TOGGLED ON");
        Thread.sleep(5000);

        led.toggle();
        System.out.println("LED TOGGLED OFF");
        Thread.sleep(5000);

        System.out.println("LED PULSE ON for 1 second");
        led.pulse(1000);

        // Shutdown Pi4J
        pi4j.shutdown();

        System.out.println("Exiting ControlGpioExample");
    }
}
