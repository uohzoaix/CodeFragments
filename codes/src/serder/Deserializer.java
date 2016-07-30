package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.io.Closeable;
import java.util.Map;

public interface Deserializer<T> extends Closeable {

    public void configure(Map<String, ?> configs, boolean isKey);

    public T deserialize(String topic, byte[] data);

    @Override
    public void close();
}
