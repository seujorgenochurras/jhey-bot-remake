package JheyBot;

import JheyBot.Commands.CommandHandlers.both.JBothHandler;
import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommand;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
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

public class Bot {

   public static ShardManager shardManager;
   public static Dotenv dotenv;
   public static String prefix = "";

   public Bot() throws LoginException {

      //Getting Token
      dotenv = Dotenv.configure().load();

      //TODO add custom prefix
      prefix = dotenv.get("PREFIX");
      DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(dotenv.get("TROLLED_BY_JOTINHA"));
      //Don't forget to enable all of them in "https://discord.com/developers/applications"
      builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));

      //:TROLLFACE:
      builder.setActivity(Activity.watching("SEU PAI DE CALCINHA"));
      builder.setStatus(OnlineStatus.ONLINE);


      shardManager = builder.build();

      //Register Listeners
      shardManager.addEventListener(new JSlashCommand());
      shardManager.addEventListener(new JPrefixCommand());
      shardManager.addEventListener(new JBothHandler());
   }


   public ShardManager shardManager() {
      return shardManager;
   }

   public static void main(String[] args) {
      //Registering all commands
      try {
         //Obviously bot is not a command lol
         Bot bot = new Bot();


         Reflections reflections = new Reflections("JheyBot.Commands", Scanners.values());
         Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CommandType.class);
         for (Class<?> classe : classes) {
            Class<?>[] interfaces = classe.getInterfaces();
            if (interfaces.length == 0) continue;
            try {
               //is this bad?
               //I really don't like the Object type here
               Object classInstance = classe.getDeclaredConstructor().newInstance();
               Method method = classe.getMethod("build");
               method.invoke(classInstance);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
               throw new RuntimeException(e);
            }
         }
      } catch (LoginException e) {
         throw new RuntimeException(e);
      }
   }
}
