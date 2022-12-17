package JheyBot.Commands.CommandHandlers.both;

import JheyBot.Bot;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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
   public void onGuildReady(@NotNull GuildReadyEvent event) {
      for(JBothHandlerInterface command : commands) {
         if (command.getOptions() == null) {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).queue();
         } else {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         }
      }
   }


   @Override
   public void onMessageReceived(@NotNull MessageReceivedEvent event) {
      String prefix = Bot.prefix;
      for(JBothHandlerInterface command : commands){
         if(event.getMessage().getContentRaw().startsWith(prefix + command.getName())){
            command.callBack(event);
            break;
         }
      }
   }

   //TODO FIX THIS REDUNDANT CODE
   @Override
   public void onGuildJoin(@NotNull GuildJoinEvent event) {
      for(JBothHandlerInterface command : commands) {
         if (command.getOptions() == null) {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).queue();
         } else {
            event.getGuild().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
         }
      }
   }

   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      for(JBothHandlerInterface command : commands){
         if(event.getName().equals(command.getName())){
            command.callBack(event);
            break;
         }
      }
   }
}
