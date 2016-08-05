import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 服务器（不限）分配算法
 * Created by zxq on 2015/10/19.
 */
public class RangePartition {
    public static void main(String[] args) {
        Map<Integer, Set<Integer>> serverBins = new HashMap<Integer, Set<Integer>>();
        int[] servers = {1, 2, 3, 4, 5};
        int[] bins = {1, 2};
        int numPerBin = servers.length / bins.length;
        int extraNum = servers.length % bins.length;
        for (int i = 0; i < bins.length; i++) {
            int start = numPerBin * i + Math.min(i, extraNum);
            int size = numPerBin;
            if (i + 1 <= extraNum)
                size += 1;
            for (int j = start; j < start + size; j++) {
                serverBins.get(bins[i]).add(servers[j]);
            }
        }
    }
}
