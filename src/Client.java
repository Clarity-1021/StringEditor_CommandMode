import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();//创建一个接收者
        String cmdLine;
        while (!(cmdLine = getCommandLine()).equals("Z")) {
//            System.out.println("您输入的字符串是:" + cmd);
            String[] strs = cmdLine.split(" ");
            switch (strs[0]) {//创建命令对象
                case "s"://显示当前编辑的字符串
                    break;
                case "A"://在尾部添加字符串（修改类命令）
                    break;
                case "a"://在头部添加字符串（修改类命令）
                    break;
                case "D"://从尾部删除指定数量的字符（修改类命令）
                    break;
                case "d"://从头部删除指定数量的字符（修改类命令）
                    break;
                case "l"://倒序列出最近执行的最多n个修改类命令的列表
                    break;
                case "u"://undo操作
                    break;
                case "r"://redo操作
                    break;
                case "m"://定义宏
                    break;
                default://执行宏
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
