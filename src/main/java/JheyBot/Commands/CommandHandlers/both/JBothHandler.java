package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Bot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class JBothHandler extends ListenerAdapter {
   public static List<JBothHandlerInterface> commands = new ArrayList<>();
   public static void add(JBothHandlerInterface command){
      commands.add(command);
   }

   @Override
   public void onReady(@NotNull ReadyEvent event) {
      for(JBothHandlerInterface command : commands) {
         if (command.getOptions() == null) {
            event.getJDA().upsertCommand(command.getName(), command.getDescription()).queue();
         } else {
            event.getJDA().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         }
      }
   }

      public final String prefix = Bot.prefix;
   @Override
   public void onMessageReceived(@NotNull MessageReceivedEvent event) {
      for(JBothHandlerInterface command : commands){
         String firstArg = event.getMessage().getContentRaw().split(" ")[0];
         boolean tmp = false;
         //TODO make this names array be native to the interface
         if(command.getNames() != null) {
            for (String name : command.getNames()) {
               if (firstArg.equals(prefix + name)) {
                  tmp = true;
                  break;
               }
            }
         }
            if(!tmp && firstArg.equals(prefix + command.getName())){
               tmp = true;
            }
            if(tmp){
               JEventObject eventObject = new JEventObject(event);
               command.callBack(eventObject);
            }
         }
      }


   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      for(JBothHandlerInterface command : commands){
         if(event.getName().equals(command.getName())){
            JEventObject eventObject = new JEventObject(event);
            command.callBack(eventObject);
            break;
         }
      }
   }
}
