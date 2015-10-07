import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;

/**
 * Created by uohzoaix on 15/10/7.
 */
public class FileDescriptor {

    private OperatingSystemMXBean osMbean;

    private static final boolean ibmvendor =
            System.getProperty("java.vendor").contains("IBM");
    private static final boolean windows =
            System.getProperty("os.name").startsWith("Windows");
    private static final boolean linux =
            System.getProperty("os.name").startsWith("Linux");

    /**
     * Constructor. Get the running Operating System instance
     */
    public FileDescriptor () {
        this.osMbean = ManagementFactory.getOperatingSystemMXBean();
    }

    /**
     * Check if the OS is unix. If using the IBM java runtime, this
     * will only work for linux.
     *
     * @return whether this is unix or not.
     */
    public boolean getUnix() {
        if (windows) {
            return false;
        }
        return (ibmvendor ? linux : true);
    }

    /**
     * Load the implementation of UnixOperatingSystemMXBean for sun jvm
     * and runs the desired method.
     * @param mBeanMethodName : method to run from the interface UnixOperatingSystemMXBean
     * @return the method result
     */
    private Long getOSUnixMXBeanMethod (String mBeanMethodName)
    {
        Object unixos;
        Class<?> classRef;
        Method mBeanMethod;

        try {
            classRef = Class.forName("com.sun.management.UnixOperatingSystemMXBean");
            if (classRef.isInstance(osMbean)) {
                mBeanMethod = classRef.getDeclaredMethod(mBeanMethodName,
                        new Class[0]);
                unixos = classRef.cast(osMbean);
                return (Long)mBeanMethod.invoke(unixos);
            }
        } catch(Exception e) {
            //LOG.warn("Not able to load class or method for com.sun.managment.UnixOperatingSystemMXBean.", e);
        }
        return null;
    }

    /**
     * Get the number of opened filed descriptor for the runtime jvm.
     * If sun java, it will use the com.sun.management interfaces.
     * Otherwise, this methods implements it (linux only).
     * @return number of open file descriptors for the jvm
     */
    public long getOpenFileDescriptorCount()
    {
        Long ofdc;

        if (!ibmvendor) {
            ofdc = getOSUnixMXBeanMethod("getOpenFileDescriptorCount");
            return (ofdc != null ? ofdc.longValue () : -1);
        }

        try {
            //need to get the PID number of the process first
            RuntimeMXBean rtmbean = ManagementFactory.getRuntimeMXBean();
            String rtname = rtmbean.getName();
            String[] pidhost = rtname.split("@");

            //using linux bash commands to retrieve info
            Process p = Runtime.getRuntime().exec(
                    new String[] { "bash", "-c",
                            "ls /proc/" + pidhost[0] + "/fdinfo | wc -l" });
            InputStream in = p.getInputStream();
            BufferedReader output = new BufferedReader(
                    new InputStreamReader(in));

            try {
                String openFileDesCount;
                if ((openFileDesCount = output.readLine()) != null) {
                    return Long.parseLong(openFileDesCount);
                }
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        } catch (IOException ie) {
            //LOG.warn("Not able to get the number of open file descriptors", ie);
        }
        return -1;
    }

    /**
     * Get the number of the maximum file descriptors the system can use.
     * If sun java, it will use the com.sun.management interfaces.
     * Otherwise, this methods implements it (linux only).
     * @return max number of file descriptors the operating system can use.
     */
    public long getMaxFileDescriptorCount()
    {
        Long mfdc;

        if (!ibmvendor) {
            mfdc = getOSUnixMXBeanMethod("getMaxFileDescriptorCount");
            return (mfdc != null ? mfdc.longValue () : -1);
        }

        try {
            //using linux bash commands to retrieve info
            Process p = Runtime.getRuntime().exec(
                    new String[] { "bash", "-c", "ulimit -n" });
            InputStream in = p.getInputStream();
            BufferedReader output = new BufferedReader(
                    new InputStreamReader(in));

            try {
                String maxFileDesCount;
                if ((maxFileDesCount = output.readLine()) != null) {
                    return Long.parseLong(maxFileDesCount);
                }
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        } catch (IOException ie) {
            //LOG.warn("Not able to get the max number of file descriptors", ie);
        }
        return -1;
    }
}