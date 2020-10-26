package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Commands.*;
import Invoker.Invoker;
import Receiver.Receiver;

public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();//创建一个接收者
        Invoker invoker = new Invoker();
        invoker.setShowLine(new ShowLine(receiver));
        invoker.setUndo(new Undo(receiver));
        invoker.setRedo(new Redo(receiver));
        String cmdLine;
        while (!(cmdLine = getCommandLine()).equals("Z")) {
//            System.out.println("您输入的字符串是:" + cmd);
            String[] strs = cmdLine.split(" ");
            switch (strs[0]) {//创建命令对象
                case "s"://显示当前编辑的字符串
                    invoker.showLine();
                    break;
                case "A"://在尾部添加字符串（修改类命令）
                    invoker.setAddLast(new AddLast(receiver, cmdLine.substring(2)));
                    invoker.addLast();
                    break;
                case "a"://在头部添加字符串（修改类命令）
                    invoker.setAddFirst(new AddFirst(receiver, cmdLine.substring(2)));
                    invoker.addFirst();
                    break;
                case "D"://从尾部删除指定数量的字符（修改类命令）
                    invoker.setDeleteLast(new DeleteLast(receiver, Integer.parseInt(strs[1])));
                    invoker.deleteLast();
                    break;
                case "d"://从头部删除指定数量的字符（修改类命令）
                    invoker.setDeleteFirst(new DeleteFirst(receiver, Integer.parseInt(strs[1])));
                    invoker.deleteFirst();
                    break;
                case "l"://倒序列出最近执行的最多n个修改类命令的列表
                    invoker.setListCommands(new ListCommands(receiver, Integer.parseInt(strs[1])));
                    invoker.listCommands();
                    break;
                case "u"://undo操作
                    invoker.undo();
                    break;
                case "r"://redo操作
                    invoker.redo();
                    break;
                case "m"://定义宏
                    invoker.setDefineMacro(new DefineMacro(receiver, Integer.parseInt(strs[1]), strs[2]));
                    invoker.defineMacro();
                    break;
                default://执行宏
                    invoker.setMacro(new Macro(receiver, strs[0].substring(1)));
                    invoker.macro();
            }
        }
    }


    /**
     * 获得当前输入的命令行
     * @return 命令行
     */
    private static String getCommandLine() {
        String cl = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("请输入一串字符串");
            cl = br.readLine();
//            System.out.println("您输入的字符串是:" + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cl;
    }
}
