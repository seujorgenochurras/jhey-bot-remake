package JheyBot.Commands.play.musicHandler;
public class UserNotInVoiceChannelException extends Exception{

   public UserNotInVoiceChannelException(){}
   public UserNotInVoiceChannelException(String message){super(message);}
   public UserNotInVoiceChannelException(String message, Throwable cause){super(message, cause);}

}
