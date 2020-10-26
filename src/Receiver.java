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
    private List<String> HistoryString = new ArrayList<>();

    /**
     * "s"
     */
    public void show() {
        System.out.println(string);
    }

    /**
     * "A"
     * @param str 需要追加的str
     */
    public void addLast(String str) {
        HistoryString.add(string);//存上一次编辑过的结果
        string += str;
    }

    /**
     * "a"
     * @param str 需要加载前面的str
     */
    public void addFirst(String str) {
        HistoryString.add(string);//存上一次编辑过的结果
        string = str + string;
    }

    /**
     * 最多删除最后的num个字母
     * @param num 需要删掉的最后的字母的个数
     */
    public void deleteLast(int num) {
        HistoryString.add(string);//存上一次编辑过的结果
        if (string.length() <= num) {
            string = "";
        }
        else {
            string = string.substring(0, string.length() - num);
        }
    }

    /**
     * 最多删除前面的num个字母
     * @param num 需要删掉的前面的字母的个数
     */
    public void deleteFirst(int num) {
        HistoryString.add(string);//存上一次编辑过的结果
        if (string.length() <= num) {
            string = "";
        }
        else {
            string = string.substring(num);
        }
    }


}
