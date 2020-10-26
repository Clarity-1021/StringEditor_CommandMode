package Commands;

import Receiver.Receiver;

public class DefineMacro implements ICommand {
    private Receiver receiver;
    private int num;
    private String macroName;

    public DefineMacro(Receiver receiver, int num, String macroName) {
        this.receiver = receiver;
        this.num = num;
        this.macroName = macroName;
    }

    @Override
    public void execute() {
        receiver.defineMacro(num, macroName);
    }
}
