package JheyBot.Commands.CommandHandlers;

import JheyBot.Commands.CommandHandlers.slashHandlers.JSlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Teste implements JSlashCommand {

   @Override
   public void callBack(SlashCommandInteractionEvent event) {
      System.out.println("instancia");
   }

   @Override
   public String getName() {
      return "null";
   }

   @Override
   public String getDescription() {
      return "null";
   }

   @Nullable
   @Override
   public List<OptionData> getOptions() {
      return null;
   }
}
