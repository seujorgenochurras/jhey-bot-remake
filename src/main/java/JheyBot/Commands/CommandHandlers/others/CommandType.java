package JheyBot.Commands.CommandHandlers.others;

import JheyBot.Commands.CommandHandlers.others.CommandTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Add this on top of each command class
* */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandType {
   CommandTypes type();
}

