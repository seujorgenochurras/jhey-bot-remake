package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Bot;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommandInterface;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//TODO make this better pls (probably with generics idk)
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
         if(firstArg.equals(prefix + command.getName())){
          JEventObject eventObject = new JEventObject(event);
            System.out.println("yes!");
            command.callBack(eventObject);
            break;
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
