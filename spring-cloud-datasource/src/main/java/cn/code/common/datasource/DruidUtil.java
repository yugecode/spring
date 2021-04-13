package cn.code.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Component;

@Component
public class DruidUtil {
    public void switchDB(DataSourceModel dataSourceModel) {
        //该Model的数据实际中应该是传参过来或去数据库查表
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceModel.getDriverClassName());
        dataSource.setUrl(dataSourceModel.getUrl());
        dataSource.setUsername(dataSourceModel.getUsername());
        dataSource.setPassword(dataSourceModel.getPassword());
        DynamicDataSourceHandler.setDataSource(dataSource);
    }
}
