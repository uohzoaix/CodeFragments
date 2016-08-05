package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by Administrator on 2016/8/5.
 */
public class FileUtil {
    /**
     * Attempts to move source to target atomically and falls back to a non-atomic move if it fails.
     *
     * @throws IOException if both atomic and non-atomic moves fail
     */
    public static void atomicMoveWithFallback(Path source, Path target) throws IOException {
        try {
            Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException outer) {
            try {
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException inner) {
                inner.addSuppressed(outer);
                throw inner;
            }
        }
    }
}
