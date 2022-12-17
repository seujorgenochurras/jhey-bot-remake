package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CommandType(type = CommandTypes.SLASH_COMMAND)
public class Play implements JSlashCommandInterface {

   private static void search(SlashCommandInteractionEvent event){
         String query = event.getOption("query").getAsString();

         if(!isURL(query)){
            query = "ytsearch:" + query +" audio";
         }
         try {
            PlayerManager.getINSTANCE().loadAndPlay(event.getMessageChannel(), event.getGuild(), query);
         } catch (Exception e){
           event.reply("Algo deu errado, pfv contate o dono `churrasco com seu jorge#2619`").queue();
            System.out.println(e.toString());
         }
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

         AudioChannel userChannel = event.getMember().getVoiceState().getChannel();
         AudioManager audioManager = event.getGuild().getAudioManager();
         audioManager.openAudioConnection(userChannel);
         search(event);
      }


   @Override
   public void callBack(SlashCommandInteractionEvent event){
      event.getChannel().sendTyping().queue();
      if(!event.getMember().getVoiceState().inAudioChannel()){
         event.reply("SEU IMBECIL ENTRA NA PORRA DE UM CANAL").setEphemeral(true).queue();
         return;
      }
      event.reply("Colocando musica").setEphemeral(true).queue();
      join(event);
   }

   @Override
   public String getName() {
      return "play";
   }

   @Override
   public String getDescription(){
      return "Plays the music";
   }

   @Override
   public List<OptionData> getOptions() {
      List<OptionData> optionData = new ArrayList<>();
      optionData.add(new OptionData(OptionType.STRING, "query", "Nome/link da musica").setRequired(true));
      return optionData;
   }


}

