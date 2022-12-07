package JheyBot.musicHandler;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackSchedule extends AudioEventAdapter {
   public final AudioPlayer audioPlayer;
   public final BlockingDeque<AudioTrack> queue;

   public TrackSchedule(AudioPlayer audioPlayer){
      this.audioPlayer = audioPlayer;
      this.queue = new LinkedBlockingDeque<>();
   }


   public void queue (AudioTrack track){
      if(!this.audioPlayer.startTrack(track, true)){

      }
   }
}
