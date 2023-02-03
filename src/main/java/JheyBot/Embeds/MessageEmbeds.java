package JheyBot.Embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import javax.annotation.Nullable;
import java.awt.*;

public final class MessageEmbeds {

   public static MessageEmbed getErrorEmbed(@Nullable String reason){
      return new EmbedBuilder()
              .setColor(Color.RED)
              .setDescription("Erro, " + reason)
              .build();
   }
   public static MessageEmbed getGenericEmbed(String message){
      return new EmbedBuilder()
              .setColor(new Color( 0, 255, 55))
              .setDescription(message)
              .build();
   }
}
