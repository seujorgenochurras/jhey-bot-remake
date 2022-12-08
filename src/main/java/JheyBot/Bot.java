package JheyBot;




import JheyBot.Commands.EventListener;
import JheyBot.Commands.play.Play;
import JheyBot.Commands.play.Skip;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;


public class Bot{

   private final ShardManager shardManager;
   private final Dotenv dotenv;

   public Bot() throws LoginException {

      dotenv = Dotenv.configure().load();

      DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(dotenv.get("TROLLED_BY_JOTINHA"));

      //Don't forget to enable all of them in "https://discord.com/developers/applications"
      builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));

      builder.setActivity(Activity.watching("SEU PAI DE CALCINHA"));
      builder.setStatus(OnlineStatus.ONLINE);
      shardManager = builder.build();

      //Register Listeners
      shardManager.addEventListener(new EventListener());
      shardManager.addEventListener(new Play());
      shardManager.addEventListener(new Skip());
   }


   public ShardManager shardManager(){
      return shardManager;
   }
   public static void main(String[] args) {
      try {
         Bot bot = new Bot();
      }catch (LoginException e){
         System.out.println("TOKEN INVALID LOGIN EXCEPTION!!!");
      }


   }

}
