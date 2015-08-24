import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/8/24.
 */
public class ReplcaceBlankLine {
    public static String replace(String text) {
        //在文本编辑器中替换可使用：\n[\s| ]*\r
        return text.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
    }

    public static void main(String[] args) {
        System.out.println(replace("dfdfdfdfdf\n" +
                "\n" +
                "fds\n" +
                "\n" +
                "\n" +
                "fdf\n" +
                "\n" +
                "s\n" +
                "\n" +
                "ddd\n" +
                "dfd"));
    }
}
