import Commands.ICommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Client {
    private static Set<String> Commands = new HashSet<>();
    static {
        Commands.add("s");
        Commands.add("A");
        Commands.add("a");
        Commands.add("D");
        Commands.add("d");
        Commands.add("l");
        Commands.add("u");
        Commands.add("m");
    }
    private List<ICommand> CommandList = new ArrayList<>();

    public static void main(String[] args) {

        String cmd;
        while (!(cmd = getCommandLine()).equals("Z")) {
            System.out.println("您输入的字符串是:" + cmd);
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
