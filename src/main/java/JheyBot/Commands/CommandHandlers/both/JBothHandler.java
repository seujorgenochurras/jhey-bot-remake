package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Bot;
import JheyBot.Commands.play.musicHandler.others.BotDisconnectedTime;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;


public class JBothHandler extends ListenerAdapter {


   //Time that bot can stay on channel without any music on
   //TODO make time changeable on runtime
   public static BotDisconnectedTime afkTime = new BotDisconnectedTime(300);
   private final String prefix = Bot.prefix;
   public static HashSet<IBothICommand> commands = new HashSet<>();
   public static void addCommand(IBothICommand command){
      commands.add(command);
   }
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
      String firstArg = event.getMessage().getContentRaw().split(" ")[0];
      IBothICommand matchingCommand = findMatchingCommand(firstArg);
      if(matchingCommand != null){
         JEventObject eventObject = new JEventObject(event);
         matchingCommand.callBack(eventObject);
      }

   }
   private IBothICommand findMatchingCommand(String firstArg) {
      //todo learn streams
      for (IBothICommand command : commands) {
         if (!firstArg.startsWith(prefix)) {
            if (firstArg.equals(command.getName())) {
               return command;
            }
         } else if ((prefix + command.getName()).equals(firstArg)) return command;
          else if (command.getNames() != null) {
               for (String name : command.getNames()) {
                  if ((prefix + name).equals(firstArg)) {
                     return command;
                  }
               }
            }
         }
      return null;
      }


   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      IBothICommand matchingCommand = findMatchingCommand(event.getName());
      if(matchingCommand != null){
         JEventObject eventObject = new JEventObject(event);
         matchingCommand.callBack(eventObject);
      }
   }
}

