package com.example.pi4j;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class ControlGpioExample {
    public static void main(String[] args) throws Exception {
        Context pi4j = Pi4J.newAutoContext();

        var config = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED Blinker")
                .address(17) // GPIO 17 (pin 11)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .build();

        var output = pi4j.create(config);

        System.out.println("Blinking LED on GPIO 17...");

        for (int i = 0; i < 10; i++) {
            output.toggle();
            Thread.sleep(500);
        }

        output.low();
        pi4j.shutdown();
    }
}
