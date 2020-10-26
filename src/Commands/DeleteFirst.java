package Commands;

import Receiver.Receiver;

public class DeleteFirst implements ICommand {
    private Receiver receiver;
    private int num;

    public DeleteFirst(Receiver receiver, int num) {
        this.receiver = receiver;
        this.num = num;
    }

    @Override
    public void execute() {
        receiver.deleteFirst(num);
    }
}
