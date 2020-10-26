package Commands;

import Receiver.Receiver;

public class DeleteLast implements ICommand {
    private Receiver receiver;
    private int num;

    public DeleteLast(Receiver receiver, int num) {
        this.receiver = receiver;
        this.num = num;
    }

    @Override
    public void execute() {
        receiver.deleteLast(num);
    }
}
