package JheyBot.Commands.play.musicHandler;

import JheyBot.Commands.CommandHandlers.both.JEventObject;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

   private static PlayerManager INSTANCE;
   private final Map<Long, GuildMusicManager> musicManagers;
   private final AudioPlayerManager audioPlayerManager;

   public PlayerManager(){
      this.musicManagers = new HashMap<>();
      this.audioPlayerManager = new DefaultAudioPlayerManager();

      AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
      AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
   }
   public GuildMusicManager getMusicManager(Guild guild){
      return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) ->{

         final GuildMusicManager guildMusicManager = new GuildMusicManager( this.audioPlayerManager);
         guild.getAudioManager().setSendingHandler(guildMusicManager.sendHandler());
         return guildMusicManager;
      });
   }
   public int trackSize = 0;
   public void loadAndPlay (JEventObject eventObject, String trackURL){
      //Local variables
      final MessageChannel textChannel = eventObject.getChannel().asGuildMessageChannel();
      final Guild guild = eventObject.getGuild();
      final GuildMusicManager musicManager = getMusicManager(guild);

      //Remove this later
      System.out.println("Musica adicionada no servidor " + guild.getName());
      SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Date date1 = new Date();
      System.out.println("Ás : " + date.format(date1));


      this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
         @Override
         public void trackLoaded(AudioTrack track) {
            trackSize++;
            musicManager.schedule.queue(track);
            textChannel.sendMessageEmbeds(new MusicEmbed(track, trackSize, eventObject).getMessageEmbed()).queue();
         }

         @Override
         public void playlistLoaded(AudioPlaylist playlist) {
            //Not an actual playlist, this is most likely to be an URL
            //TODO Playlist probably doesn't work (fix)
            trackSize++;
            List<AudioTrack> tracks = playlist.getTracks();
            if(!tracks.isEmpty()){
               musicManager.schedule.queue(tracks.get(0));
               textChannel.sendMessageEmbeds(new MusicEmbed(tracks, trackSize, eventObject).getMessageEmbed()).queue();
            }
         }
         @Override
         public void noMatches() {
            textChannel.sendMessage("Não encontrei a musica desculpe ;-;").queue();
         }

         @Override
         public void loadFailed(FriendlyException exception) {
            textChannel.sendMessage("Alguma coisa deu errado, pfv contate o dono ``churrasco com seu jorge#2619``").queue();
         }
      });
   }

   /**
    *  Converts milliseconds into a string mm:ss
    *
    * @param millis milliseconds
    * **/
   private static String getTime(long millis){
      String result = "";
      long minutes = (millis / 1000)  / 60;
      int seconds = (int)((millis / 1000) % 60);
      result += minutes + ":";
      result += (seconds < 10) ? "0" + seconds : seconds;
      return result;
   }
   public static PlayerManager getInstance(){
      if(INSTANCE == null){
         INSTANCE = new PlayerManager();
      }
      return INSTANCE;
   }

   private static class MusicEmbed{
      //TODO vai tocar em: (tempo até tocar )
      private final MessageEmbed musicEmbed;
      MusicEmbed(@NotNull AudioTrack track, int size, @NotNull JEventObject eventObject){
         musicEmbed = buildEmbed(track, size, eventObject);
      }
         MusicEmbed(@NotNull List<AudioTrack> tracks, int size, @NotNull JEventObject eventObject){
        musicEmbed = buildEmbed(tracks.get(size-1), size, eventObject);
      }
      public MessageEmbed getMessageEmbed(){
         return this.musicEmbed;
      }
      private MessageEmbed buildEmbed(AudioTrack track, int size, JEventObject eventObject){
       return new EmbedBuilder()
                 .setTitle("Colocando Música \n" + track.getInfo().title, track.getInfo().uri)
                 .setColor(new Color( 0, 255, 55))
                 .addBlankField(false)
                 .addField(new MessageEmbed.Field("Duração: ", getTime(track.getDuration()) , true ))
                 .addField(new MessageEmbed.Field("Posição na fila", Integer.toString(size), true))
                 .setThumbnail("https://img.youtube.com/vi/" + track.getIdentifier() + "/mqdefault.jpg") //video thumb
                 .setFooter(eventObject.getMember().getUser().getName(), eventObject.getUser().getAvatar().getUrl())
                 .build();
      }

   }
}
