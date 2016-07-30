package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.util.Map;

public class ByteSerializer implements Serializer<Bytes> {

    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    public byte[] serialize(String topic, Bytes data) {
        if (data == null)
            return null;

        return data.get();
    }

    public void close() {
        // nothing to do
    }
}
