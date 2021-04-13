package cn.code.controller;

import cn.code.common.datasource.DataSourceModel;
import cn.code.common.response.ResponseTemplate;
import cn.code.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    /**
     * 获取goods，查询默认数据源
     *
     * @return
     */
    @GetMapping("/default")
    public ResponseTemplate getGoods() {
        return ResponseTemplate.ok(dataSourceService.getGoods());
    }

    /**
     * 获取goods，通过注解（动态数据源）
     *
     * @return
     */
    @GetMapping("/annotation")
    public ResponseTemplate getGoodsByAnnotation() {
        DataSourceModel model = new DataSourceModel();
        model.setDriverClassName("com.mysql.cj.jdbc.Driver");
        model.setPassword("password");
        model.setUsername("root");
        model.setUrl("jdbc:mysql://192.168.6.4:31392/goods?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
        return ResponseTemplate.ok(dataSourceService.getGoodsByAnnotation(model));
    }

    /**
     * 获取goods，通过代码方式
     *
     * @return
     */
    @GetMapping("/code")
    public ResponseTemplate getGoodsByCode() {
        return ResponseTemplate.ok(dataSourceService.getGoodsByCode());
    }
}
