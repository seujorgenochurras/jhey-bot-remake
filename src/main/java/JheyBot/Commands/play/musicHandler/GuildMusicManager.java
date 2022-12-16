package JheyBot.Commands.play.musicHandler;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
   public final AudioPlayer audioPlayer;
   public final TrackSchedule schedule;
   private final AudioPlayerSendHandler sendHandler;
   public GuildMusicManager(AudioPlayerManager audioPlayerManager){
      this.audioPlayer = audioPlayerManager.createPlayer();
      this.schedule = new TrackSchedule(this.audioPlayer);
      this.audioPlayer.addListener(this.schedule);

      this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
   }
   public AudioPlayerSendHandler sendHandler(){
      return this.sendHandler;
   }
}
