package JheyBot.Commands;

import JheyBot.Bot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Play extends ListenerAdapter {

   @Override
      public void onMessageReceived(MessageReceivedEvent event) {
      User user = event.getAuthor();
      String message = event.getMessage().getContentRaw();
      if (user.isBot()) return;

      if (message.startsWith("!play")) {

      }
   }

      private void join(Member member){
         if(member.getVoiceState().inAudioChannel()){
         }
      }

   @Override
   public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
      String command = event.getName();
      if(command.equals("play")){
         OptionMapping optionMapping = event.getOption("query");
         String userTag = event.getUser().getAsTag();
         event.reply("SUGMA DICKKK " + userTag + " " + optionMapping.getAsString()).setEphemeral(true).queue();
         if(!event.getMember().getVoiceState().inAudioChannel()){
            event.reply("SEU IMBECIL ENTRA NA PORRA DE UM CANAL").queue();
            return;
         }
         AudioChannel userChannel = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
         audioManager.openAudioConnection(userChannel);
      }
   }

   @Override
   public void onGuildReady(GuildReadyEvent event) {
      List<CommandData> commandDataList = new ArrayList<>();
      OptionData query = new OptionData(OptionType.STRING, "query", "Searches for the music", true);
      commandDataList.add(Commands.slash("play", "Plays youtube stuff").addOptions(query));
      event.getGuild().updateCommands().addCommands(commandDataList).queue();
   }

   private void joinChannel(){

   }
}

