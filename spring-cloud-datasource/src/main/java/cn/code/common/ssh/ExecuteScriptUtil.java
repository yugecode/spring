package cn.code.common.ssh;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.code.common.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author luojiayu
 * @date 2021/1/4
 */
@Slf4j
public class ExecuteScriptUtil {
    private static final int TIME_OUT = 1000 * 100 * 60;

    public static String createExec(String ip, Integer port, String userName, String password,
                                    String cmd, String charset) throws Exception {
        log.info("远程执行脚本请求参数：ip:{},port:{},userName:{},password:{},cmd:{}", ip, port, userName, password, cmd);
        Connection conn = new Connection(ip, port);
        conn.connect();
        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        try {
            if (conn.authenticateWithPassword(userName, password)) {

                Session session = conn.openSession();
                session.execCommand(cmd);

                stdOut = new StreamGobbler(session.getStdout());
                outStr = processStream(stdOut, charset);
                System.out.println("outStr:-->" + outStr);

                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);
                System.out.println("outErr:-->" + outErr);

                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                session.close();
            } else {
                throw new AppException("登录远程机器失败" + ip);
            }
        } finally {
            conn.close();
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        log.info("远程执行脚本方法完成。。。");
        return outErr;
    }

    /**
     * 测试远程连接
     *
     * @return
     * @author luojiayu
     * @date 2021/1/19
     */
    public static Boolean testConnection(String ip, Integer port, String userName, String password) {
        log.info("测试远程连接：ip:{},port:{},userName:{},password:{},cmd:{}", ip, port, userName, password);
        Connection conn = new Connection(ip, port);
        boolean flag;
        try {
            conn.connect();
            flag = conn.authenticateWithPassword(userName, password);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return flag;
    }

    /**
     * 将流中的字符串读取出来
     *
     * @param in
     * @param charset
     * @return
     */
    private static String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int read = 0;
        while ((read = in.read(buf)) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }


}
