package JheyBot.Commands.play;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.others.CommandTypes;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommandInterface;
import JheyBot.Commands.play.musicHandler.PlayerManager;
import JheyBot.Commands.play.musicHandler.UserNotInVoiceChannelException;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

import static JheyBot.Commands.play.Play.isURL;

@CommandType(type = CommandTypes.PREFIX_COMMAND)
public class PlayPrefix implements JPrefixCommandInterface {

   //TODO FIX THIS SHITTY CODE
   @Override
   public String getName() {
      return "play";
   }

   @Override
   public void callBack(MessageReceivedEvent event) {
     event.getChannel().sendTyping().queue();
      try{
      join(event);
     }catch (UserNotInVoiceChannelException e){
         event.getMessage().reply("SEU IMBECIL VOCÊ NÃO TA EM UM CANAL BURRÃO").queue();
      }
   }
   private void search(MessageReceivedEvent event){
      StringBuilder query = new StringBuilder();
      List<String> args = getArgs(event);
      System.out.println(args);
      for(String arg : args){
         query.append(arg);
      }

      if(!isURL(query.toString())){
         query = new StringBuilder(new StringBuilder("ytsearch:" + query + " audio"));
      }
      try {
         PlayerManager.getINSTANCE().loadAndPlay(event.getChannel(), event.getGuild(), query.toString());
      } catch (Exception e){
         event.getMessage().reply("Algo deu errado, pfv contate o dono `churrasco com seu jorge#2619`").queue();
         System.out.println(e.toString());
      }
   }

   public void join(MessageReceivedEvent event) throws UserNotInVoiceChannelException {
      boolean voiceState = event.getMember().getVoiceState().inAudioChannel();
      if(!voiceState) throw new UserNotInVoiceChannelException("User is not on a voice channel");

      AudioChannel userChannel = event.getMember().getVoiceState().getChannel();
      AudioManager audioManager = event.getGuild().getAudioManager();
      audioManager.openAudioConnection(userChannel);
      search(event);
   }
}
