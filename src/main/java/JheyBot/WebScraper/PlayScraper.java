package JheyBot.WebScraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class PlayScraper {
   WebClient webClient = new WebClient();
    HtmlPage page;
   private String URL;

   public String getURL() {
      return URL;
   }


   private HtmlPage getWebPage(String url) throws IOException {
      webClient.getOptions().setCssEnabled(false);
      webClient.getOptions().setJavaScriptEnabled(false);
      return webClient.getPage(url);
   }
   private boolean startsWithList(String[] arr, String msg){
      for(String s : arr){
         if(msg.startsWith(s)){
            return true;
         }
      }
      return false;
   }
   private final String[] safeURLs = {
           "https://www.youtube.com/watch?v=",
           "https://youtu.be/"
   };

   public void setURL(String URL) throws IOException {
      if(startsWithList(safeURLs, URL)){
         page = getWebPage(URL);
      }
      this.URL = URL;
   }



   public PlayScraper(String URL){

   }

}
