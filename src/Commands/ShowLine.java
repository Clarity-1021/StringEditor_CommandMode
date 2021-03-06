package Commands;

import Receiver.Receiver;

public class ShowLine implements ICommand {
    private Receiver receiver;

    public ShowLine(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.show();
    }
}
