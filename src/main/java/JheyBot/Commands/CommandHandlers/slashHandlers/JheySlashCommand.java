package JheyBot.Commands.CommandHandlers.slashHandlers;

import JheyBot.Bot;
import JheyBot.Commands.CommandHandlers.JheyCommand;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JheySlashCommand extends JheyCommand {
   private String name;
   private String description;

   private List<OptionData> options = new ArrayList<>();
   private static List<CommandData> commandData = new ArrayList<>();

   public String getDescription() {
      return description;
   }

   public void setDescription(@NotNull String description) {
      this.description = description;
   }

   public String getName() {
      return name;
   }

   public void setName(@NotNull String name) {
      this.name = name;
   }

   @Override
   public void build(){
      if(!options.isEmpty()){
         commandData.add(Commands.slash(this.name, this.description).addOptions(options));
      }
      commandData.add(Commands.slash(this.name, this.description));
      Bot.shardManager.addEventListener(this);
   }

   @Override
   public void onGuildReady(GuildReadyEvent event) {
      event.getGuild().updateCommands().addCommands(commandData).queue();
   }

   public void addOption(@NotNull OptionData option){
      options.add(option);

   }

   @Override
   public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
      if(event.getName().equals(this.name)){
      /*TODO ADD CALLBACK HERE
      AAAAA
      AAAA
       */
      }
   }
}
