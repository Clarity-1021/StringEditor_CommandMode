package Commands;

import java.util.HashSet;
import java.util.Set;

public class CommandInfo {
    /**
     * 指令类型
     */
    String cmd;

    /**
     * 指令参数
     */
    String arg;

    /**
     * 指令修改的内容
     */
    String argUndo;

    public CommandInfo(String cmd, String arg) {
        this.cmd = cmd;
        this.arg = arg;
    }

    public String getCmdLine() {
        return cmd + " " + arg;
    }

    public void setArgUndo(String argUndo) {
        this.argUndo = argUndo;
    }

    public String getCmd() {
        return this.cmd;
    }

    public String getArg() {
        return this.arg;
    }

    public String getArgUndo() {
        return argUndo;
    }
}
