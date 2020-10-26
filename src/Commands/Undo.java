package Commands;

import Receiver.Receiver;

public class Undo implements ICommand {
    private Receiver receiver;

    public Undo(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.undo();
    }
}
