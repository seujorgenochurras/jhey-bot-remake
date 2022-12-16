package JheyBot.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TestCommand extends ListenerAdapter {

   @Override
   public void onMessageReceived(@NotNull MessageReceivedEvent event) {
      if(event.getAuthor().isBot()) return;
      if(event.getMessage().getContentRaw().startsWith("j!t")){
         List<String> args = new ArrayList<>(List.of(event.getMessage().getContentRaw().split(" ")));

         args.remove(0);
         event.getMessage().reply("You have " + args.size() + " args").queue();
         event.getChannel().sendMessage("Here comes a list of your args").queueAfter(1, TimeUnit.SECONDS);
         event.getChannel().sendMessage(args.toString()).queue();

      }
   }
}
