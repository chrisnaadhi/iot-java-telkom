import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.Callback;
import kong.unirest.UnirestException;

public class RESTCall {

  public void sendDataOverRest(double temp) {
    Unirest.post("https://api.thingspeak.com/update.json")
        .header("accept", "application/json")
        .field("api_key", "XXXXXX") // Replace with your API key
        .field("field1", temp)
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

  public static void main(String[] args) throws InterruptedException {
    RESTCall http = new RESTCall();

    double temp = 30.0;
    http.sendDataOverRest(temp);

    // Wait to allow async call to complete before exiting
    Thread.sleep(3000);

    // Properly shutdown Unirest
    Unirest.shutDown();
  }
}