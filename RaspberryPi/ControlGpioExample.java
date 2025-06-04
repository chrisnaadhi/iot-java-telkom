import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class ControlGpioExample {
    public static void main(String[] args) throws Exception {
        Context pi4j = Pi4J.newAutoContext();

        // Define multiple GPIO pins (e.g., 17, 27, 22)
        int[] gpioPins = { 17, 27, 22 };

        // Create DigitalOutput instances for each pin
        DigitalOutput[] outputs = new DigitalOutput[gpioPins.length];

        for (int i = 0; i < gpioPins.length; i++) {
            var config = DigitalOutput.newConfigBuilder(pi4j)
                    .id("pin" + gpioPins[i])
                    .name("GPIO " + gpioPins[i])
                    .address(gpioPins[i])
                    .shutdown(DigitalState.LOW)
                    .initial(DigitalState.LOW)
                    .build();
            outputs[i] = pi4j.create(config);
        }

        // Blink each pin ON for 1 second sequentially
        for (int i = 0; i < outputs.length; i++) {
            System.out.println("Turning ON GPIO " + gpioPins[i]);
            outputs[i].high();
            Thread.sleep(1000);
            outputs[i].low();
            System.out.println("Turning OFF GPIO " + gpioPins[i]);
        }

        pi4j.shutdown();
        System.out.println("Done.");
    }
}
