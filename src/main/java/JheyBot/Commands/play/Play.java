package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.both.JEventTypes;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@CommandType(type = CommandTypes.BOTH)
public class Play implements JBothHandlerInterface {

   //TODO FIX THIS SHITTY CODE
   private void search(JEventObject event){
      //I really dont like the StringBuilder here
      StringBuilder query = new StringBuilder();
      if(event.getType().equals(JEventTypes.MessageReceivedEvent)){
        try {
         List<String> args = getArgs(event);
           for(String arg : args){
              query.append(arg + " ");
           }
           System.out.println(query);
        }catch (NullPointerException e){
           event.reply("QUE MUSICA?");
           return;
        }
      } else {
         query = new StringBuilder(event.getOption("query").getAsString());
      }
      if(!isURL(query.toString())){
         query = new StringBuilder("ytsearch:" + query + " audio");
      }
      try {
         PlayerManager.getInstance().loadAndPlay(event.getChannel(), event.getGuild(), query.toString());
      } catch (Exception e){
         event.getMessage().reply("Algo deu errado, pfv contate o dono `churrasco com seu jorge#2619`").queue();
         System.out.println(e.toString());
      }
   }

   public void join(JEventObject event) throws UserNotInVoiceChannelException {
      boolean voiceState = event.getMember().getVoiceState().inAudioChannel();
      if(!voiceState) throw new UserNotInVoiceChannelException("User is not on a voice channel");
      AudioChannel userChannel = event.getMember().getVoiceState().getChannel();
      AudioManager audioManager = event.getGuild().getAudioManager();
      audioManager.openAudioConnection(userChannel);
      search(event);
   }
   public static boolean isURL(String url){
      try{
         new URI(url);
         return true;
      } catch (URISyntaxException e){
         return false;
      }
   }
   @Override
   public String getName() {
      return "play";
   }

   @Override
   public String getDescription() {
      return "Plays an youtube music";
   }

   @Override
   public void callBack(JEventObject event) {
      event.replyAsPhemeral("Colocando musica");
      try{
         join(event);
      }catch (UserNotInVoiceChannelException e){
         event.getChannel().sendMessage("SEU IMBECIL ENTRA NA PORRA DE UM CANAL").queue();
      }
   }
   @Override
   public List<OptionData> getOptions() {
      List<OptionData> optionData = new ArrayList<>();
      optionData.add(new OptionData(OptionType.STRING, "query", "Nome/link da musica").setRequired(true));
      return optionData;
   }

   @Override
   public String[] getNames() {
      return new String[]{"p", "Play", "P", "PLAY"};
   }
}
