package JheyBot.Commands.CommandHandlers.slashHandlers;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;


public class JSlashCommand extends ListenerAdapter {

   private static final HashSet<ISlashCommand> commands = new HashSet<>();

   public static void add(ISlashCommand command){
     commands.add(command);
   }

   @Override
   public void onReady(@NotNull ReadyEvent event) {
      for(ISlashCommand command : commands) {
         if (command.getOptions() == null) {
            event.getJDA().upsertCommand(command.getName(), command.getDescription()).queue();
         } else {
            event.getJDA().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         }
      }
   }

   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      for(ISlashCommand command : commands){
         if(event.getName().equals(command.getName())){
            command.callBack(event);
            break;
         }
      }
   }
}
