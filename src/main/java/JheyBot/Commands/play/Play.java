package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.both.JBothHandlerInterface;
import JheyBot.Commands.CommandHandlers.both.JEventObject;
import JheyBot.Commands.CommandHandlers.both.JEventTypes;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.Embeds.MessageEmbeds;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.SpotifyHandler;
import JheyBot.Commands.play.musicHandler.others.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//TODO make this work with spotify
@CommandType(type = CommandTypes.BOTH)
public class Play implements JBothHandlerInterface {
   private void search(JEventObject event) {
      String query;
      try {
      if (event.getType().equals(JEventTypes.MessageReceivedEvent)) {
            List<String> args = getArgs(event);
            query = String.join(" ", args);
         } else{
            query = event.getOption("query").getAsString();
         }
         System.out.println(query);
      if (!isURL(query) || SpotifyHandler.isSpotifyURL(query)) {
         if(SpotifyHandler.isSpotifyURL(query)){
            query = new SpotifyHandler(query).getTrackName();
         }
         query = "ytsearch:" + query + " audio";
      }

            PlayerManager.getInstance().loadAndPlay(event, query);
         }  catch (NullPointerException e) {
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Que musica?")).queue();
      } catch (Exception e) {
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Erro totalmente inesperado, contate o dono `churrasco com seu jorge#2619`")).queue();
            System.out.println(e.toString());
         }
      }


   public void join(JEventObject event) throws UserNotInVoiceChannelException {
      UserNotInVoiceChannelException.getUserVoiceState(event.getMember());

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
         event.getChannel().sendMessageEmbeds(MessageEmbeds.getErrorEmbed("Você não está em um canal")).queue();
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
