/**
 * Created by uohzoaix on 15/8/22.
 */
public class StringToXMLString {
    private static final char[] hexchars = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'};

    /**
     * @param s
     * @return
     */
    static String toXMLString(String s) {
        if (s == null)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < s.length(); idx++) {
            char ch = s.charAt(idx);
            if (ch == '<') {
                sb.append("&lt;");
            } else if (ch == '&') {
                sb.append("&amp;");
            } else if (ch == '%') {
                sb.append("%25");
            } else if (ch < 0x20) {
                sb.append("%");
                sb.append(hexchars[ch / 16]);
                sb.append(hexchars[ch % 16]);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    static private int h2c(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else if (ch >= 'A' && ch <= 'F') {
            return ch - 'A';
        } else if (ch >= 'a' && ch <= 'f') {
            return ch - 'a';
        }
        return 0;
    }

    /**
     * @param s
     * @return
     */
    static String fromXMLString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < s.length(); ) {
            char ch = s.charAt(idx++);
            if (ch == '%') {
                char ch1 = s.charAt(idx++);
                char ch2 = s.charAt(idx++);
                char res = (char) (h2c(ch1) * 16 + h2c(ch2));
                sb.append(res);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}
