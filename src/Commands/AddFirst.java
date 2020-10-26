package Commands;

import Receiver.Receiver;

public class AddFirst implements ICommand {
    private Receiver receiver;
    private String str;

    public AddFirst(Receiver receiver, String str) {
        this.receiver = receiver;
        this.str = str;
    }

    @Override
    public void execute() {
        receiver.addFirst(str);
    }
}
