package JheyBot.Commands.CommandHandlers.both;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.nio.channels.Channel;
import java.util.List;


//TODO stop being lazy and implements something
public class JEventObject {
   private Message message;
   private SlashCommandInteraction interaction;

   private JEventTypes type;

   public JEventTypes getType(){
      return type;
   }

   public SlashCommandInteraction getInteraction() {
      return interaction;
   }

   public void setInteraction(SlashCommandInteraction interaction) {
      this.interaction = interaction;
   }

   public Message getMessage() {
      return this.message;
   }

   public void setMessage(Message message) {
      this.message = message;
   }

   public void reply(String message){
      if(this.type.equals(JEventTypes.SlashCommandInteractionEvent)) {
         this.getInteraction().reply(message).queue();
      } else {
      getMessage().reply(message).queue();
      }
   }
   public void replyAsPhemeral(String message){
      if(this.type.equals(JEventTypes.SlashCommandInteractionEvent)){
          getInteraction().reply(message).setEphemeral(true).queue();
      } else {
      getMessage().reply(message).queue();
      }
   }

   public Guild getGuild(){
      if(this.type.equals(JEventTypes.SlashCommandInteractionEvent)){
         return this.getInteraction().getGuild();
      }
      return this.getMessage().getGuild();
   }

   public User getUser(){
      return this.getMember().getUser();
   }

   public List<OptionMapping> getOptions(){
      return this.interaction.getOptions();
   }

   public OptionMapping getOption(String name){
      return this.getInteraction().getOption("query");
   }
   public Member getMember(){
      if(this.type.equals(JEventTypes.SlashCommandInteractionEvent)){
      return this.interaction.getMember();
      }
      return this.message.getMember();
   }
   public MessageChannelUnion getChannel(){
      if(this.type.equals(JEventTypes.SlashCommandInteractionEvent)) {
         return this.getInteraction().getChannel();
      }
      return this.getMessage().getChannel();
   }
   public JEventObject(MessageReceivedEvent event){
      setMessage(event.getMessage());
      this.type = JEventTypes.MessageReceivedEvent;

   }
   public JEventObject(SlashCommandInteractionEvent event){
      setInteraction(event.getInteraction());
      this.type = JEventTypes.SlashCommandInteractionEvent;
   }
}
