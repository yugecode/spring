package cn.code.service;

import cn.code.common.datasource.DataSource;
import cn.code.common.datasource.DataSourceModel;
import cn.code.common.datasource.DruidUtil;
import cn.code.common.datasource.DynamicDataSourceHandler;
import cn.code.dao.DataSourceDao;
import cn.code.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luojiayu
 * @date 2021/4/13
 */
@Service
public class DataSourceService {

    @Autowired
    private DataSourceDao dataSourceDao;

    @Autowired
    private DruidUtil druidUtil;

    public List<Goods> getGoods() {
        return dataSourceDao.selectGoods();
    }

    /**
     * 获取goods，通过注解（动态数据源）
     *
     * @return
     */
    @DataSource
    public List<Goods> getGoodsByAnnotation(DataSourceModel model) {
        return dataSourceDao.selectGoods();
    }

    /**
     * 获取goods，通过代码方式
     *
     * @return
     */
    public List<Goods> getGoodsByCode() {
        DataSourceModel model = new DataSourceModel();
        model.setDriverClassName("com.mysql.cj.jdbc.Driver");
        model.setPassword("password");
        model.setUsername("root");
        model.setUrl("jdbc:mysql://192.168.6.4:31392/goods?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
        druidUtil.switchDB(model);
        List<Goods> goods = dataSourceDao.selectGoods();
        DynamicDataSourceHandler.clear();
        return goods;
    }
}
