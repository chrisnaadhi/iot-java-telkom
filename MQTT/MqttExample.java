import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttExample {
  public static void main(String[] args) {
    String topic = "PX Temperature and Humidity";
    String content = "T=30C and RH=40%";
    int qos = 2;
    String broker = "tcp://192.168.1.17:1883";
    String clientId = "JavaMQTTKelompok5";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos);
      message.setRetained(true); // Important for late subscribers

      System.out.println("Publishing message: " + content);
      sampleClient.publish(topic, message);
      System.out.println("Message published");

      Thread.sleep(1000); // Let the broker process the message

      sampleClient.disconnect();
      System.out.println("Disconnected");

    } catch (MqttException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
