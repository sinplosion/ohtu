package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.fluent.Request;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        List<Player> playersL = Arrays.asList(players);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        
        System.out.println("Players from FIN " + dtf.format(now) + "\n");
        
        playersL.stream().filter(p -> p.getNationality().equals("FIN")).sorted().forEach(p -> System.out.println(p));
        
    }
  
}
