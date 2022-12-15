package JheyBot.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingJ extends ListenerAdapter {

   @Override
   public void onMessageReceived(MessageReceivedEvent event) {
      if(event.getAuthor().isBot()) return;
      if(event.getMessage().toString().equals("ping")){
         event.getMessage().reply("Pong!").queue();
      }
   }
}
