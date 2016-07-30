package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.util.Map;

public class IntegerSerializer implements Serializer<Integer> {

    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    public byte[] serialize(String topic, Integer data) {
        if (data == null)
            return null;

        return new byte[] {
                (byte) (data >>> 24),
                (byte) (data >>> 16),
                (byte) (data >>> 8),
                data.byteValue()
        };
    }

    public void close() {
        // nothing to do
    }
}
