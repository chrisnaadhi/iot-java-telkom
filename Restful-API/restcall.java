import java.io.InputStream;
import java.net.http.HttpResponse;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RESTCall implements Callback<JsonNode> {
  public void sendDataOverRest(double temp) {
    Unirest.post("https://api.thingspeak.com/update.json")
        .header("accept", "application/json")
        .field("api_key", "XXXXXX")
        .field("field1", temp)
        .asJsonAsync(this);
  }

  @Override
  public void cancelled() {
    System.out.println("The request has been cancelled.");
  }

  @Override
  public void completed(HttpResponse<JsonNode> response) {
    int code = response.getStatus();

    JsonNode body = response.getBody();
    InputStream rawBody = response.getRawBody();

    System.out.println(code);
    System.out.println(body);
    System.out.println(rawbody)
  }

  @Override
  public void failed(UnirestException arg0) {
    System.out.println("The request has failed.");
    arg0.printStackTrace();
  }

  public statuc void main(String[] args) throws InterruptedException {
    RESTCall http = new RestCall();


    double temp = 30.0;
    http.sendDataOverRest(temp);
  }
}

// Command to run the code:
// javac -classpath "/path/to/unirest.jar" RESTCall.java
// sudo java -classpath ".:/path/to/unirest.jar" RESTCall
// Note: Replace "/path/to/unirest.jar" with the actual path to the Unirest
// library.