package JheyBot.Commands.CommandHandlers.slashHandlers;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class JheySlashCommand2 extends ListenerAdapter {

   public static List<JSlashCommand> commands = new ArrayList<>();
   public static void add(JSlashCommand command){
     commands.add(command);
   }

   @Override
   public void onGuildReady(GuildReadyEvent event) {
      for(JSlashCommand command : commands) {
         if (command.getOptions() == null) {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).queue();
         } else {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         }
      }
   }


   @Override
   public void onReady(ReadyEvent event) {
     
   }

   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      for(JSlashCommand command : commands){
         if(event.getName().equals(command.getName())){
            command.callBack(event);
            break;
         }
      }

   }
}
