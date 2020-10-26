package Commands;

import Receiver.Receiver;

public class ListCommands implements ICommand {
    private Receiver receiver;
    private int num;

    public ListCommands(Receiver receiver, int num) {
        this.receiver = receiver;
        this.num = num;
    }

    @Override
    public void execute() {
        receiver.list(num);
    }
}
