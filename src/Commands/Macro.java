package Commands;

import Receiver.Receiver;

public class Macro implements ICommand {
    private Receiver receiver;
    private String macroName;

    public Macro(Receiver receiver, String macroName) {
        this.receiver = receiver;
        this.macroName = macroName;
    }

    @Override
    public void execute() {
        receiver.macro(macroName);
    }
}
