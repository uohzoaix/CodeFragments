package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.io.Closeable;
import java.util.Map;

public interface Serializer<T> extends Closeable {

    /**
     * 根据isKey从configs读取对应的属性值
     */
    public void configure(Map<String, ?> configs, boolean isKey);

    /**
     * 序列化
     */
    public byte[] serialize(String topic, T data);


    @Override
    public void close();
}
