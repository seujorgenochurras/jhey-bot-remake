package JheyBot.Commands.play.musicHandler;

import JheyBot.Bot;
import JheyBot.Commands.play.Play;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public class SpotifyHandler {
   private final String URL;

   public static boolean isSpotifyURL(String URL){
      if(!Play.isURL(URL)) return false;
      return URL.startsWith("https://open.spotify.com/");
   }
   private boolean isSpotifyTrack(){
         if(!Play.isURL(this.URL)) return false;
      return this.URL.startsWith("https://open.spotify.com/track/");
   }
   public SpotifyHandler(String URL){
      this.URL = URL;
   }
   public String getTrackName() throws IOException, ParseException, SpotifyWebApiException {
      if(!isSpotifyTrack()) return null;
      SpotifyApi spotifyApi = Bot.spotifyApi;
      String trackID = this.URL.substring(31, 53);
      System.out.println(spotifyApi.getTrack(trackID).build().execute().getName());
      System.out.println("Oi");
      System.out.println(trackID);
      return spotifyApi.getTrack(trackID).build().execute().getName();
   }

}
