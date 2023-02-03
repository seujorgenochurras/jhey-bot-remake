package JheyBot.CommandHandlers.both;

import JheyBot.Bot;
import JheyBot.Commands.play.musicHandler.others.BotDisconnectedTime;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;



public class JBothHandler extends ListenerAdapter {


   //Time that bot can stay on channel without any music on
   //TODO make time changeable on runtime
   public static BotDisconnectedTime afkTime = new BotDisconnectedTime(300);
   public static void addCommand(JBothHandlerInterface command){
      commands.add(command);
   }
   private final String prefix = Bot.prefix;
   public static HashSet<JBothHandlerInterface> commands = new HashSet<>();
   @Override
   public void onReady(@NotNull ReadyEvent event) {
      commands.forEach((command)->{
         if(command.getOptions() != null){
            event.getJDA().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         } else{
           event.getJDA().upsertCommand(command.getName(), command.getDescription()).queue();
         }
      });
   }

   @Override
   public void onMessageReceived(@NotNull MessageReceivedEvent event) {
      if(event.getAuthor().isBot()) return;
      String firstArg = event.getMessage().getContentRaw().split(" ")[0];
      JBothHandlerInterface matchingCommand = findMatchingCommand(firstArg);
      if(matchingCommand != null){
         JEventObject eventObject = new JEventObject(event);
         matchingCommand.callBack(eventObject);
      }

   }
   @Nullable
   private JBothHandlerInterface findMatchingCommand(@NotNull String firstArg) {
      //this is for slash commands
      final String finalArg = firstArg.startsWith(prefix) ?
              firstArg.replace(prefix, "") : firstArg;
      return commands.stream().filter(command -> command.getName().equals(finalArg) ||
                      (command.getNames() != null &&
                              Arrays.asList(command.getNames()).contains(finalArg)
                      ))
              .findFirst().
              orElse(null);
   }
   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      JBothHandlerInterface matchingCommand = findMatchingCommand(event.getName());
      if(matchingCommand != null){
         JEventObject eventObject = new JEventObject(event);
         matchingCommand.callBack(eventObject);
      }
   }
}

