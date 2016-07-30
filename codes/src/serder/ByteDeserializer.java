package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */

import java.util.Map;

public class ByteDeserializer implements Deserializer<Bytes> {

    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    public Bytes deserialize(String topic, byte[] data) {
        if (data == null)
            return null;

        return new Bytes(data);
    }

    public void close() {
        // nothing to do
    }
}
