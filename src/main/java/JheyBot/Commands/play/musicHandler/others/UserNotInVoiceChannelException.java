package JheyBot.Commands.play.musicHandler.others;

import net.dv8tion.jda.api.entities.Member;

public final class UserNotInVoiceChannelException extends IllegalArgumentException{

   public UserNotInVoiceChannelException(){}
   public UserNotInVoiceChannelException(String message){super(message);}
   public UserNotInVoiceChannelException(String message, Throwable cause){super(message, cause);}


   public static void getUserVoiceState(Member member){
      if(!member.getVoiceState().inAudioChannel()) throw new UserNotInVoiceChannelException("User is not on a voice channel");

   }


}
