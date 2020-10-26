package Commands;

import Receiver.Receiver;

public class AddLast implements ICommand {
    private Receiver receiver;
    private String str;

    public AddLast(Receiver receiver, String str) {
        this.receiver = receiver;
        this.str = str;
    }

    @Override
    public void execute() {
        receiver.addLast(str);
    }
}
