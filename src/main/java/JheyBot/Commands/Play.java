package JheyBot.Commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Play extends ListenerAdapter {

   @Override
      public void onMessageReceived(MessageReceivedEvent event) {
         User user = event.getAuthor();
         String message = event.getMessage().getContentRaw();
         if(message.startsWith("!play"))
            event.getChannel().sendMessage("IAMETER NO CU DA SAI").queue();
      }
   }

