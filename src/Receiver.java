import Commands.CommandInfo;
import Commands.ICommand;

import java.util.*;

public class Receiver {
    private String string = "";
    /**
     * 所有指令
     */
    private static Set<String> Commands = new HashSet<>();
    static {
        Commands.add("s");
        Commands.add("A");
        Commands.add("a");
        Commands.add("D");
        Commands.add("d");
        Commands.add("l");
        Commands.add("u");
        Commands.add("r");
        Commands.add("m");
    }

    /**
     * 宏的名字->宏对应的指令的Map
     */
    private Map<String, List<ICommand>> MacroMap = new HashMap<>();

    /**
     * 指令历史记录
     */
    private List<CommandInfo> HistoryCommands = new ArrayList<>();

    private int curCmd = -1;

    /**
     * "s"
     */
    public void show() {
        System.out.println(string);
    }

    public void showUndo() {
        System.out.println(string);
    }

    /**
     * "A"
     * @param str 需要追加的str
     */
    public void addLast(String str) {
        CommandInfo cmdInfo = new CommandInfo("A", str);
        cmdInfo.setArgUndo(str.length() + "");//存这一次追加的str的长度
        string += str;
        HistoryCommands.add(cmdInfo);
        curCmd++;
        show();
    }

    public void addLastUndo(int num) {
        if (string.length() <= num) {
            string = "";
        }
        else {
            string = string.substring(0, string.length() - num);
        }
        show();
    }

    /**
     * "a"
     * @param str 需要加载前面的str
     */
    public void addFirst(String str) {
        CommandInfo cmdInfo = new CommandInfo("a", str);
        cmdInfo.setArgUndo(str.length() + "");//存这一次增加在最前的str
        string = str + string;
        HistoryCommands.add(cmdInfo);
        curCmd++;
        show();
    }

    public void addFirstUndo(int num) {
        if (string.length() <= num) {
            string = "";
        }
        else {
            string = string.substring(num);
        }
        show();
    }

    /**
     * "D"
     * 最多删除最后的num个字母
     * @param num 需要删掉的最后的字母的个数
     */
    public void deleteLast(int num) {
        CommandInfo cmdInfo = new CommandInfo("D", num + "");
        String argUndo;
        if (string.length() <= num) {
            argUndo = string;//存这一次删掉的str
            string = "";
        }
        else {
            argUndo = string.substring(string.length() - num);//存这一次删掉的str
            string = string.substring(0, string.length() - num);
        }
        cmdInfo.setArgUndo(argUndo);
        HistoryCommands.add(cmdInfo);
        curCmd++;
        show();
    }

    public void deleteLastUndo(String str) {
        string += str;
        show();
    }

    /**
     * "d"
     * 最多删除前面的num个字母
     * @param num 需要删掉的前面的字母的个数
     */
    public void deleteFirst(int num) {
        CommandInfo cmdInfo = new CommandInfo("d", num + "");
        String argUndo;
        if (string.length() <= num) {
            argUndo = string;//存这一次删掉的str
            string = "";
        }
        else {
            argUndo = string.substring(0, num);//存这一次删掉的str
            string = string.substring(num);
        }
        cmdInfo.setArgUndo(argUndo);
        HistoryCommands.add(cmdInfo);
        curCmd++;
        show();
    }

    public void deleteFirstUndo(String str) {
        string = str + string;
        show();
    }

    public void defineMacro(int num, String macroName) {

    }

    /**
     * "u"
     */
    public void undo() {
        if (curCmd == -1) {
            return;
        }
        CommandInfo lastCmdInfo = HistoryCommands.get(curCmd--);//获得当前需要撤回的指令
        switch (lastCmdInfo.getCmd()) {//创建命令对象
            case "A"://在尾部添加字符串（修改类命令）
                addLastUndo(Integer.parseInt(lastCmdInfo.getArgUndo()));
                break;
            case "a"://在头部添加字符串（修改类命令）
                addFirstUndo(Integer.parseInt(lastCmdInfo.getArgUndo()));
                break;
            case "D"://从尾部删除指定数量的字符（修改类命令）
                deleteLastUndo(lastCmdInfo.getArgUndo());
                break;
            case "d"://从头部删除指定数量的字符（修改类命令）
                deleteFirstUndo(lastCmdInfo.getArgUndo());
                break;
            default://执行宏
        }
    }

    /**
     * "l"
     * @param num 需要列出的指令的个数
     */
    public void list(int num) {
        num = Math.min(num, curCmd + 1);
        for (int i = 0; i <= num - 1; i++) {
            System.out.println(i + " " + HistoryCommands.get(curCmd - i).getCmdLine());
        }
    }

}
