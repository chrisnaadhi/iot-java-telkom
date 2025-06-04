import java.io.InputStream;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RESTCall implements Callback<JsonNode> {
  public void sendDataOverRest(double temp) {
    Unirest.post("https://api.thingspeak.com/update.json")
      .header("accept", "application/json")
  }
}