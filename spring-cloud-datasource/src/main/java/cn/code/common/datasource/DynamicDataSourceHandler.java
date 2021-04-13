package cn.code.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSourceHandler extends AbstractRoutingDataSource {
    //初始化主数据源到ThreadLocal中
    private static final ThreadLocal<DataSource> DATASOURCE =
        ThreadLocal.withInitial(() -> (DataSource) SpringContextUtil.getBean("defaultDataSource"));
    //用于存储当前的数据源，url作为map的key,避免造成数据库的多次连接导致数据库连接数增多，数据库崩溃
    private static Map<String, DataSource> map = new ConcurrentHashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return getDataSource();
    }


    public static DataSource getDataSource() {
        return DynamicDataSourceHandler.DATASOURCE.get();
    }

    public static void setDataSource(DataSource dataSource) {
        //设置数据源是，首先去判断map中是否存在该数据源，若存在则进行使用，不存在则加入
        //避免数据库的连接增加
        DruidDataSource druidDataSource = (DruidDataSource) DynamicDataSourceHandler.DATASOURCE.get();
        if (map.get(druidDataSource.getUrl()) != null) {
            DynamicDataSourceHandler.DATASOURCE.set(DynamicDataSourceHandler.DATASOURCE.get());
        } else {
            DynamicDataSourceHandler.DATASOURCE.set(dataSource);
            map.put(druidDataSource.getUrl(), dataSource);
        }
    }

    public static void clear() {
        DynamicDataSourceHandler.DATASOURCE.remove();
        setDataSource((DataSource) SpringContextUtil.getBean("defaultDataSource"));
    }
}
