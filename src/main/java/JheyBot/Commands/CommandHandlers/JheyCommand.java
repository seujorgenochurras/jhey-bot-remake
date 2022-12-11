package JheyBot.Commands.CommandHandlers;

import JheyBot.Bot;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JheyCommand extends ListenerAdapter {


   public static void addCommand(){


   }
   public void build(){
      Bot.shardManager.addEventListener(this);
   }


}
