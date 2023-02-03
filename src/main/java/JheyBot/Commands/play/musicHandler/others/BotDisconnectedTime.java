package JheyBot.Commands.play.musicHandler.others;

import net.dv8tion.jda.api.entities.Guild;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public final class BotDisconnectedTime{
   private final int seconds;
   private Guild guild;

   public Guild getGuild() {
      return guild;
   }
   public void setGuild(Guild guild) {
      this.guild = guild;
   }

   public BotDisconnectedTime(int seconds){
   this.seconds = seconds;
   }
   private Timer timer;
   private final TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
         getGuild().getAudioManager().closeAudioConnection();
      }
   };
   public void start() {
      timer = new Timer();
      timer.schedule(timerTask, TimeUnit.SECONDS.toMillis(seconds));
   }
   public void stop(){
      timer.cancel();
      timer = new Timer();
   }
}
