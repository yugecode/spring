package cn.code.common.utils;

/**
 * twitter的snowflake算法 -- java实现
 *
 * @author beyond
 * @date 2016/11/26
 */
public class SnowFlake {
    private static SnowFlake snowFlake = new SnowFlake(1);

    /**
     * 起始的时间戳 2021-01-12 17:28:49
     */
    private static final long START_TIMESTAMP = 1610443729000L;

    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 13;
    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 10;

    /**
     * 每一部分的最大值
     */
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    /**
     * 机器标识
     */
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    protected SnowFlake(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
    }

    /**
     * 获取16进制字符串
     * Benchmark                         Mode  Cnt        Score        Error  Units
     * SnowFlakeBenchmarkTest.UUID      thrpt    3  1645939.352 ± 237554.294  ops/s
     * SnowFlakeBenchmarkTest.javaUUID  thrpt    3  2019917.195 ± 116812.556  ops/s
     */
    public static String uuid() {
        return String.format("%016x", snowFlake.nextId());
    }

    /**
     * 产生下一个ID
     */
    public synchronized long nextId() {
        long currentTimestamp = getCurrentTimestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currentTimestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currentTimestamp = getNextSecond(lastTimestamp);
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;
        //时间戳部分 | 机器标识部分 | 序列号部分
        return (currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT
            | machineId << MACHINE_LEFT
            | sequence;
    }

    /**
     * Get next millisecond
     */
    private long getNextSecond(long lastTimestamp) {
        long timestamp = getCurrentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentTimestamp();
        }

        return timestamp;
    }

    /**
     * Get current millisecond，
     */
    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}