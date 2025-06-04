import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MorseExample {
  final static GpioController gpio = GpioFactory.getInstance();
  final static GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MorseLED", PinState.HIGH);

  public static void main(String[] args) throws InterruptedException {
    pin.setShutdownOptions(true, PinState.LOW);
    System.out.println("Morse Coe 'A' - Dot, Dash, Shortspace");
    dot();
    dash();
    shortSpace();
    gpio.shutdown();
  }

  static void dot() throws InterruptedException {
    pin.high();
    Thread.sleep(300);
    pin.low();
    Thread.sleep(300);
  }

  static void dash() throws InterruptedException {
    pin.high();
    Thread.sleep(900);
    pin.low();
    Thread.sleep(300);
  }

  static void shortSpace() throws InterruptedException {
    Thread.sleep(600);
  }
}