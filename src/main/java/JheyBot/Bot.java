package JheyBot;

import JheyBot.Commands.CommandHandlers.Teste;
import JheyBot.Commands.CommandHandlers.slashHandlers.CommandAnnotation;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import JheyBot.Commands.CommandHandlers.slashHandlers.JheySlashCommand2;
import JheyBot.Commands.play.Skip;
import JheyBot.Commands.play.Stop;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Bot{

   public static ShardManager shardManager;
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
      shardManager.addEventListener(new JheySlashCommand2());

   }


   public ShardManager shardManager(){
      return shardManager;
   }

   public static void main(String[] args) {

         Reflections reflections = new Reflections("JheyBot.Commands.play", Scanners.values());
         Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CommandAnnotation.class);
         classes.forEach((Class<?> classe) ->{
            if(classe.getInterfaces()[0] != null && classe.getInterfaces()[0].getSimpleName().equals("JSlashCommand")){
               try {
                  JSlashCommand morte = (JSlashCommand) classe.getDeclaredConstructor().newInstance();
                  Method method = classe.getMethod("getInstance");
                  JheySlashCommand2.add((JSlashCommand) method.invoke(morte));
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                        InstantiationException e) {
                  throw new RuntimeException(e);
               }
            }
         });

      try {
         Bot bot = new Bot();
      }catch (LoginException e){
         System.out.println("TOKEN INVALID LOGIN EXCEPTION!!!");
      }


   }

}
