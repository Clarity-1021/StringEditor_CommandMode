package Commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandInfo {
    /**
     * 指令类型
     */
    String cmd;

    /**
     * 指令字符参数
     */
    String argStr;

    /**
     * 指令数字参数
     */
    int argNum;

    /**
     * 指令修改的串
     */
    String editStr;

    /**
     * 指令修改的数字
     */
    int editNum;

    List<CommandInfo> undoCmdList;

    public CommandInfo(String cmd) {
        this.cmd = cmd;
    }

    public CommandInfo(String cmd, String argStr) {
        this.cmd = cmd;
        this.argStr = argStr;
    }

    public CommandInfo(String cmd, int argNum) {
        this.cmd = cmd;
        this.argNum = argNum;
    }

    public String getEditStr() {
        return editStr;
    }

    public String getArgStr() {
        return argStr;
    }

    public int getArgNum() {
        return argNum;
    }

    public int getEditNum() {
        return editNum;
    }

    public List<CommandInfo> getUndoCmdList() {
        return undoCmdList;
    }

    public String getCmdLine() {
        switch (cmd) {
            case "A"://加
            case "a":
                return cmd + " " + argStr;
            case "D"://删
            case "d":
                return cmd + " " + argNum;
            default://宏
                return cmd;
        }
    }

    public String getCmd() {
        return this.cmd;
    }

    public void setEditStr(String editStr) {
        this.editStr = editStr;
    }

    public void setEditNum(int editNum) {
        this.editNum = editNum;
    }

    public void setUndoCmdList(List<CommandInfo> undoCmdList) {
        this.undoCmdList = undoCmdList;
    }
}
