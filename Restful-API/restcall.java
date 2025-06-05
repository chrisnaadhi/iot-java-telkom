import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.Callback;
import kong.unirest.UnirestException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RESTCall {

  public void sendDataOverRest(double temp) {
    Unirest.post("https://api.thingspeak.com/update.json")
        .header("accept", "application/json")
        .field("api_key", "PC46E8S7BT7ENFQ5") // Replace with your API key
        .field("field1", String.format("%.2f", temp))
        .asJsonAsync(new Callback<JsonNode>() {
          @Override
          public void cancelled() {
            System.out.println("The request has been cancelled.");
          }

          @Override
          public void completed(HttpResponse<JsonNode> response) {
            int code = response.getStatus();
            JsonNode body = response.getBody();

            System.out.println("Status code: " + code);
            System.out.println("Response body: " + body);
          }

          @Override
          public void failed(UnirestException e) {
            System.out.println("The request has failed.");
            e.printStackTrace();
          }
        });
  }

  public static void main(String[] args) {
    RESTCall http = new RESTCall();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    AtomicInteger count = new AtomicInteger(0);
    int maxRuns = 30;

    ScheduledFuture<?> scheduledTask = scheduler.scheduleAtFixedRate(() -> {
      int current = count.incrementAndGet();

      // Generate a random temperature between 20.0 and 40.0
      double temp = 20.0 + (Math.random() * 20.0);
      System.out.printf("Sending reading #%d: %.2f°C%n", current, temp);

      http.sendDataOverRest(temp);

      if (current >= maxRuns) {
        System.out.println("Reached max runs. Shutting down scheduler...");
        scheduler.shutdown();

        try {
          Unirest.shutDown();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, 0, 10, TimeUnit.SECONDS);
  }
}
