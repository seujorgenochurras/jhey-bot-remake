package JheyBot;

import JheyBot.Commands.CommandHandlers.Command;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
import JheyBot.Commands.CommandHandlers.slashHandlers.JheySlashCommand;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.security.auth.login.LoginException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Bot{

   public static ShardManager shardManager;
   private final Dotenv dotenv;

   public Bot() throws LoginException {

      //Getting Token

      dotenv = Dotenv.configure().load();
      DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(dotenv.get("TROLLED_BY_JOTINHA"));

      //Don't forget to enable all of them in "https://discord.com/developers/applications"
      builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));

      //:TROLLFACE:
      builder.setActivity(Activity.watching("SEU PAI DE CALCINHA"));
      builder.setStatus(OnlineStatus.ONLINE);


      shardManager = builder.build();
      //Register Listeners
      shardManager.addEventListener(new JheySlashCommand());

   }


   public ShardManager shardManager(){
      return shardManager;
   }

   public static void main(String[] args) {
      //Registering all commands
         Reflections reflections = new Reflections("JheyBot.Commands.play", Scanners.values());
         Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Command.class);
         classes.forEach((Class<?> classe) ->{
            if(classe.getInterfaces()[0] != null && classe.getInterfaces()[0].getSimpleName().equals("JSlashCommandInterface")){
               try {
                  JSlashCommandInterface classInstance = (JSlashCommandInterface) classe.getDeclaredConstructor().newInstance();
                  Method method = classe.getMethod("build");
                  method.invoke(classInstance);
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                        InstantiationException e) {
                  throw new RuntimeException(e);
               }
            }
         });


      //Loging in
      try {
         Bot bot = new Bot();
      }catch (LoginException e){
         System.out.println("TOKEN INVALID LOGIN EXCEPTION!!!");
      }


   }

}
