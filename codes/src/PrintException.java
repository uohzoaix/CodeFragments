import java.io.*;

/**
 * Created by Administrator on 2015/8/24.
 */
public class PrintException {
    /**
     * 获取异常信息，不打印在终端
     * @param exception
     * @return
     */
    public static String getExceptionMessage(Throwable exception) {
        StringWriter out = new StringWriter();
        exception.printStackTrace(new PrintWriter(out));
        BufferedReader in = new BufferedReader(new StringReader(out.toString()));
        try {
            StringBuilder str = new StringBuilder();
            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                if (str.length() > 0) {
                    str.append(", ");
                }
                str.append(line);
            }
            return str.toString();
        } catch (IOException e) {
            // ignore
        }
        return "n/a";
    }
}
