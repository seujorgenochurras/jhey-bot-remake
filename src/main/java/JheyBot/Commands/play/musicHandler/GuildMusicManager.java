package JheyBot.Commands.play.musicHandler;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {
   public final AudioPlayer audioPlayer;
   public final TrackSchedule schedule;
   private final AudioPlayerSendHandler sendHandler;
   public GuildMusicManager(AudioPlayerManager audioPlayerManager, Guild guild){
      this.audioPlayer = audioPlayerManager.createPlayer();
      this.schedule = new TrackSchedule(this.audioPlayer, guild);
      this.audioPlayer.addListener(this.schedule);

      this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
   }
   public AudioPlayerSendHandler sendHandler(){
      return this.sendHandler;
   }
}
