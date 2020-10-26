package Commands;

import Receiver.Receiver;

public class Redo implements ICommand {
    private Receiver receiver;

    public Redo(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.redo();
    }
}
