package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.util.Map;

public class IntegerDeserializer implements Deserializer<Integer> {

    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    public Integer deserialize(String topic, byte[] data) {
        if (data == null)
            return null;
        if (data.length != 4) {
            throw new SerializerException("Size of data received by IntegerDeserializer is " +
                    "not 4");
        }

        int value = 0;
        for (byte b : data) {
            value <<= 8;
            value |= b & 0xFF;
        }
        return value;
    }

    public void close() {
        // nothing to do
    }
}