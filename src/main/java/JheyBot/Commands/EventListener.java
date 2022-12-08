package JheyBot.Commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static JheyBot.Commands.play.Play.join;
import static JheyBot.Commands.play.Skip.skipMusic;

public class EventListener extends ListenerAdapter {

   @Override
   public void onGuildReady(GuildReadyEvent event) {
      List<CommandData> commandDataList = new ArrayList<>();
      OptionData query = new OptionData(OptionType.STRING, "query", "Searches for the music", true);
      commandDataList.add(Commands.slash("play", "Plays youtube stuff").addOptions(query));

      commandDataList.add(Commands.slash("skip", "Skips the currently music"));

      event.getGuild().updateCommands().addCommands(commandDataList).queue();
   }

   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      String command = event.getName();
      if(command.equals("play")){
         event.reply("Enviando musica...").queue();
         join(event);
      } else if(command.equals("skip")){
         if(!event.getMember().getVoiceState().inAudioChannel()){
            event.reply("VOCE NEM TA EM UM CANAL KRL").queue();
            return;
         }
         event.reply("Pulando musica").queue();
         skipMusic(event);
      }
   }


}
