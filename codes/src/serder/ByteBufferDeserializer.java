package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.nio.ByteBuffer;
import java.util.Map;

public class ByteBufferDeserializer implements Deserializer<ByteBuffer> {

    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    public ByteBuffer deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        return ByteBuffer.wrap(data);
    }

    public void close() {
        // nothing to do
    }
}
