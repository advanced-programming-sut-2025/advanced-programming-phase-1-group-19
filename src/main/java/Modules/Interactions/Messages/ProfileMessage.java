package Modules.Interactions.Messages;

import Modules.Interactions.Commands.ProfileCommand;

public record ProfileMessage(ProfileCommand command, String message) implements Message {

    @Override
    public boolean isNull() {
        return command == null;
    }

    @Override
    public boolean isMessageEmpty() {
        return message.isEmpty();
    }
}
