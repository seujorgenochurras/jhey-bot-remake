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
     String firstArg = event.getMessage().getContentRaw().split(" ")[0];
     JBothHandlerInterface matchingCommand = findMatchingCommand(firstArg);
     if(matchingCommand != null){
        JEventObject eventObject = new JEventObject(event);
        matchingCommand.callBack(eventObject);
     }

   }
      private JBothHandlerInterface findMatchingCommand(String firstArg){
         for(JBothHandlerInterface command: commands){
            if(command.getNames() != null){
               for(String name : command.getNames()){
                  if(name.equals(prefix + firstArg)){
                     return command;
                  }
               }
            } else if (command.getName().equals(prefix + firstArg)) {
               return command;
            }
         }
            return null;
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
