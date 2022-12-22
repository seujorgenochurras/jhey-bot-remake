package JheyBot.Commands.play.musicHandler;

import JheyBot.Commands.CommandHandlers.both.JEventObject;
import net.dv8tion.jda.api.entities.Guild;

public class BotNotInVoiceChannelException extends IllegalStateException{
   public BotNotInVoiceChannelException(String message){
      super(message);
   }
   public BotNotInVoiceChannelException(String message, Throwable cause){super(message, cause);}
   public BotNotInVoiceChannelException(){super();}

   public static void getOccur(Guild guild){
      if(!guild.getAudioManager().isConnected()) throw new BotNotInVoiceChannelException("Bot não está em um canal");
   }

}
