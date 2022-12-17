package JheyBot;

import JheyBot.Commands.CommandHandlers.others.CommandType;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommand;
import JheyBot.Commands.CommandHandlers.prefixHandlers.JPrefixCommandInterface;
import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommandInterface;
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

public class Bot{

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
   }


   public ShardManager shardManager(){
      return shardManager;
   }

   public static void main(String[] args) {
      //Registering all commands
         Reflections reflections = new Reflections("JheyBot.Commands", Scanners.values());
         Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CommandType.class);
         classes.forEach((Class<?> classe) -> {
                    Class<?>[] interfaces = classe.getInterfaces();
                    if (interfaces.length == 0) return;
                     //TODO find a way to fix this messy code
                    if (interfaces[0].equals(JSlashCommandInterface.class)) {
                       try {
                          JSlashCommandInterface classInstance = (JSlashCommandInterface) classe.getDeclaredConstructor().newInstance();
                          Method method = classe.getMethod("build");
                          method.invoke(classInstance);
                       } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                                InstantiationException e) {
                          throw new RuntimeException(e);
                       }
                    } else if (interfaces[0].equals(JPrefixCommandInterface.class)) {
                       try {
                          JPrefixCommandInterface classInstance = (JPrefixCommandInterface) classe.getDeclaredConstructor().newInstance();
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
