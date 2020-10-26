package Receiver;

import Commands.CommandInfo;
import Commands.ICommand;

import java.util.*;

public class Receiver {
    private String string = "";

    /**
     * 宏的名字->宏对应的指令的Map
     */
    private Map<String, List<CommandInfo>> MacroMap = new HashMap<>();

    /**
     * 指令历史记录
     */
    private List<CommandInfo> HistoryCommands = new ArrayList<>();

    private int curCmd = -1;

    private void clearRedo() {
        if (HistoryCommands.size() == 0) {
            return;
        }

        if (curCmd < HistoryCommands.size() - 1) {
            if (curCmd == -1) {
                HistoryCommands = new ArrayList<>();
            }
            else {
                List listToRemove = HistoryCommands.subList(curCmd + 1, HistoryCommands.size());
                HistoryCommands.removeAll(listToRemove);
            }
        }
    }

    /**
     * "s"
     */
    public void show() {
        System.out.println(string);
    }

    /**
     * "l"
     * @param num 需要列出的指令的个数
     */
    public void list(int num) {
        num = Math.min(num, curCmd + 1);
        for (int i = 0; i <= num - 1; i++) {
            System.out.println((i + 1) + " " + HistoryCommands.get(curCmd - i).getCmdLine());
        }
    }

    /**
     * "m"
     * @param num 需要存最近的num个修改命令
     * @param macroName 宏的名字
     */
    public void defineMacro(int num, String macroName) {
        if (curCmd + 1 < num) {
            return;
        }
        List<CommandInfo> cmdList = new ArrayList<>();
        for (int i = num - 1; i >= 0; i--) {
            CommandInfo curCommand = HistoryCommands.get(curCmd - i);
            switch (curCommand.getCmd()) {//查看命令
                case "A"://在尾部添加字符串（修改类命令）
                    cmdList.add(new CommandInfo("A", curCommand.getArgStr()));
                    break;
                case "a"://在头部添加字符串（修改类命令）
                    cmdList.add(new CommandInfo("a", curCommand.getArgStr()));
                    break;
                case "D"://从尾部删除指定数量的字符（修改类命令）
                    cmdList.add(new CommandInfo("D", curCommand.getArgNum()));
                    break;
                case "d"://从头部删除指定数量的字符（修改类命令）
                    cmdList.add(new CommandInfo("d", curCommand.getArgNum()));
                    break;
                default://执行宏
                    cmdList.add(new CommandInfo(curCommand.getCmd()));
            }
        }

        MacroMap.put(macroName, cmdList);
    }

    public int addLastDo(String str) {
        string += str;
        return str.length();
    }

    public CommandInfo getCmdInfo_A(String str) {
        CommandInfo cmdInfo = new CommandInfo("A", str);
        cmdInfo.setEditNum(addLastDo(str));//存这一次追加的str的长度
        return cmdInfo;
    }

    /**
     * "A"
     * @param str 需要追加的str
     */
    public void addLast(String str) {
        clearRedo();
        HistoryCommands.add(getCmdInfo_A(str));
        curCmd++;
        show();
    }

    public int addFirstDo(String str) {
        string = str + string;
        return str.length();
    }

    public CommandInfo getCmdInfo_a(String str) {
        CommandInfo cmdInfo = new CommandInfo("a", str);
        cmdInfo.setEditNum(addFirstDo(str));//存这一次增加在最前的str
        return cmdInfo;
    }

    /**
     * "a"
     * @param str 需要加载前面的str
     */
    public void addFirst(String str) {
        clearRedo();
        HistoryCommands.add(getCmdInfo_a(str));
        curCmd++;
        show();
    }

    public String deleteLastDo(int num) {
        String argUndo;
        if (string.length() <= num) {
            argUndo = string;//存这一次删掉的str
            string = "";
        }
        else {
            argUndo = string.substring(string.length() - num);//存这一次删掉的str
            string = string.substring(0, string.length() - num);
        }
        return argUndo;
    }

    public CommandInfo getCmdInfo_D(int num) {
        CommandInfo cmdInfo = new CommandInfo("D", num);
        cmdInfo.setEditStr(deleteLastDo(num));
        return cmdInfo;
    }

    /**
     * "D"
     * 最多删除最后的num个字母
     * @param num 需要删掉的最后的字母的个数
     */
    public void deleteLast(int num) {
        clearRedo();
        HistoryCommands.add(getCmdInfo_D(num));
        curCmd++;
        show();
    }


    public String deleteFirstDo(int num) {
        String argUndo;
        if (string.length() <= num) {
            argUndo = string;//存这一次删掉的str
            string = "";
        }
        else {
            argUndo = string.substring(0, num);//存这一次删掉的str
            string = string.substring(num);
        }
        return argUndo;
    }

    public CommandInfo getCmdInfo_d(int num) {
        CommandInfo cmdInfo = new CommandInfo("d", num);
        cmdInfo.setEditStr(deleteFirstDo(num));
        return cmdInfo;
    }

    /**
     * "d"
     * 最多删除前面的num个字母
     * @param num 需要删掉的前面的字母的个数
     */
    public void deleteFirst(int num) {
        clearRedo();
        HistoryCommands.add(getCmdInfo_d(num));
        curCmd++;
        show();
    }

    public List<CommandInfo> macroDo(List<CommandInfo> cmdList) {
        List<CommandInfo> editCmdList = new ArrayList<>();
        for (CommandInfo cmdInfo : cmdList) {
            switch (cmdInfo.getCmd()) {//查看命令
                case "A"://在尾部添加字符串（修改类命令）
                    editCmdList.add(getCmdInfo_A(cmdInfo.getArgStr()));
                    break;
                case "a"://在头部添加字符串（修改类命令）
                    editCmdList.add(getCmdInfo_a(cmdInfo.getArgStr()));
                    break;
                case "D"://从尾部删除指定数量的字符（修改类命令）
                    editCmdList.add(getCmdInfo_D(cmdInfo.getArgNum()));
                    break;
                case "d"://从头部删除指定数量的字符（修改类命令）
                    editCmdList.add(getCmdInfo_d(cmdInfo.getArgNum()));
                    break;
                default://执行宏
                    editCmdList.addAll(macroDo(MacroMap.get(cmdInfo.getCmd())));
            }
        }

        return editCmdList;
    }

    public void macroUndo(List<CommandInfo> cmdList) {
        for (CommandInfo cmdInfo : cmdList) {
            switch (cmdInfo.getCmd()) {//查看命令
                case "A"://在尾部添加字符串（修改类命令）
                    deleteLastDo(cmdInfo.getEditNum());
                    break;
                case "a"://在头部添加字符串（修改类命令）
                    deleteFirstDo(cmdInfo.getEditNum());
                    break;
                case "D"://从尾部删除指定数量的字符（修改类命令）
                    addLastDo(cmdInfo.getEditStr());
                    break;
                case "d"://从头部删除指定数量的字符（修改类命令）
                    addFirstDo(cmdInfo.getEditStr());
                    break;
                default://执行宏
                    macroDo(MacroMap.get(cmdInfo.getCmd()));
            }
        }
    }

    public CommandInfo getCmdInfo_m(String macroName) {
        List<CommandInfo> cmdList = MacroMap.get(macroName);
        CommandInfo cmdInfo = new CommandInfo(macroName);
        List<CommandInfo> undoCmdList = macroDo(cmdList);
        List<CommandInfo> reverseList = new ArrayList<>();
        for (int i = undoCmdList.size() - 1; i >= 0; i--) {
            reverseList.add(undoCmdList.get(i));
        }
        cmdInfo.setUndoCmdList(reverseList);
        return cmdInfo;
    }

    public void macro(String macroName) {
        if (!MacroMap.containsKey(macroName)) {
            return;
        }
        clearRedo();
        HistoryCommands.add(getCmdInfo_m(macroName));
        curCmd++;
        show();
    }

    /**
     * "u"
     */
    public void undo() {
        if (curCmd == -1) {
            return;
        }
        CommandInfo lastCmdInfo = HistoryCommands.get(curCmd--);//获得当前需要撤回的指令
        switch (lastCmdInfo.getCmd()) {//查看命令
            case "A"://在尾部添加字符串（修改类命令）
                deleteLastDo(lastCmdInfo.getEditNum());
                break;
            case "a"://在头部添加字符串（修改类命令）
                deleteFirstDo(lastCmdInfo.getEditNum());
                break;
            case "D"://从尾部删除指定数量的字符（修改类命令）
                addLastDo(lastCmdInfo.getEditStr());
                break;
            case "d"://从头部删除指定数量的字符（修改类命令）
                addFirstDo(lastCmdInfo.getEditStr());
                break;
            default://执行宏
                macroUndo(lastCmdInfo.getUndoCmdList());
        }
        show();
    }

    public void redo() {
        if (curCmd == HistoryCommands.size() - 1) {
            return;
        }

        CommandInfo nextCmdInfo = HistoryCommands.get(++curCmd);//获得当前需要重做的指令
        switch (nextCmdInfo.getCmd()) {//查看命令
            case "A"://在尾部添加字符串（修改类命令）
                addLastDo(nextCmdInfo.getArgStr());
                break;
            case "a"://在头部添加字符串（修改类命令）
                addFirstDo(nextCmdInfo.getArgStr());
                break;
            case "D"://从尾部删除指定数量的字符（修改类命令）
                deleteLastDo(nextCmdInfo.getArgNum());
                break;
            case "d"://从头部删除指定数量的字符（修改类命令）
                deleteFirstDo(nextCmdInfo.getArgNum());
                break;
            default://执行宏
                macroDo(MacroMap.get(nextCmdInfo.getCmd()));
        }
        show();
    }

}
