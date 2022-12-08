package JheyBot.Commands.play;

import JheyBot.musicHandler.PlayerManager;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.URI;
import java.net.URISyntaxException;


public class Play extends ListenerAdapter {

      private static void search(SlashCommandInteractionEvent event){
         String query = event.getOption("query").getAsString();

         if(!isURL(query)){
            query = "ytsearch:" + query +" audio";
         }
         PlayerManager.getINSTANCE().loadAndPlay(event.getChannel().asTextChannel(), query);
      }

      public static boolean isURL(String url){
         try{
            new URI(url);
            return true;
         } catch (URISyntaxException e){
            return false;
         }
      }
      public static void join(SlashCommandInteractionEvent event){
         if(!event.getMember().getVoiceState().inAudioChannel()){
            event.reply("SEU IMBECIL ENTRA NA PORRA DE UM CANAL").queue();
            return;
         }
         AudioChannel userChannel = event.getMember().getVoiceState().getChannel();
         AudioManager audioManager = event.getGuild().getAudioManager();
         audioManager.openAudioConnection(userChannel);
         search(event);
      }



}

