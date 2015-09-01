import java.lang.reflect.Method;

/**
 * Created by zxq on 2015/9/1.
 */
public class GenerateMethodTag {
    /**
     * 生成一个方法的唯一标识
     * 可用作方法缓存，key为该唯一标识，value为method返回的值
     * @param method 方法
     * @return 唯一标识
     */
    public static String generate(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getDeclaringClass().getName());
        sb.append("@");
        sb.append(method.getName());
        Class<?>[] types = method.getParameterTypes();
        for (Class<?> clazz : types) {
            sb.append("@" + clazz.getName());
        }
        return sb.toString();
    }
}
