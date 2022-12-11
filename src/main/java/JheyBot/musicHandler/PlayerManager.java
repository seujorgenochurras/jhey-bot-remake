package JheyBot.musicHandler;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

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
   public <T extends MessageChannel> void loadAndPlay (T textChannel, Guild guild, String trackURL){
      //Need guild as a parameter because i cant find a message channel that includes getGuild() and sendMessage() at the same time
      //if found PLS FIX
      final GuildMusicManager musicManager = getMusicManager(guild);

      this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
         @Override
         public void trackLoaded(AudioTrack track) {
         musicManager.schedule.queue(track);
         textChannel.sendMessage(" Colocando Musica na Fila `**"
                 + track.getInfo().title
                 + "**Por**"
                 + track.getInfo().author + "`**").queue();
         }

         @Override
         public void playlistLoaded(AudioPlaylist playlist) {
               final List<AudioTrack> tracks = playlist.getTracks();
               if(!tracks.isEmpty()){
                  musicManager.schedule.queue(tracks.get(0));
                  textChannel.sendMessage(" Colocando Musica na Fila `**"
                          + tracks.get(0).getInfo().title
                          + "**Por**"
                          + tracks.get(0).getInfo().author + "`**").queue();
               }
         }

         @Override
         public void noMatches() {
            textChannel.sendMessage("Não encontrei a musica desculpe ;-;").queue();
         }

         @Override
         public void loadFailed(FriendlyException exception) {
            textChannel.sendMessage("Alguma coisa deu errado exception : " + exception.toString()).queue();
         }
      });
   }
   public static PlayerManager getINSTANCE(){
      if(INSTANCE == null){
         INSTANCE = new PlayerManager();
      }
      return INSTANCE;
   }

}
