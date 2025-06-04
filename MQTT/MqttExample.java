import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttExample {
  public static void main(Stringp[] args) {
    String topic = "PX Temperature and Humidity";
    String content = "T=30C and RH=40%";
    int qoe = 2;       
    String broker = "tcp://broker.hivemq.com:1883";
    String clientId = "JavaMQTTExample";

    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MMqTTClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions;
      connOpts.setCleanSession(true)
      System.out.println("Connecting to broker: " + broker);
    }
  }
}