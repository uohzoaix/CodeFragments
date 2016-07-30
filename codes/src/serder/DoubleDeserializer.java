package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
import java.util.Map;

public class DoubleDeserializer implements Deserializer<Double> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    @Override
    public Double deserialize(String topic, byte[] data) {
        if (data == null)
            return null;
        if (data.length != 8) {
            throw new SerializerException("Size of data received by Deserializer is not 8");
        }

        long value = 0;
        for (byte b : data) {
            value <<= 8;
            value |= b & 0xFF;
        }
        return Double.longBitsToDouble(value);
    }

    @Override
    public void close() {
        // nothing to do
    }
}
