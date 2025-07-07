package Models.Interactions.Messages;

import Models.Interactions.Commands.MainCommand;

public record MainMessage(MainCommand command, String message) implements Message {

    @Override
    public boolean isNull() {
        return command == null;
    }

    @Override
    public boolean isMessageEmpty() {
        return message.isEmpty();
    }
}
