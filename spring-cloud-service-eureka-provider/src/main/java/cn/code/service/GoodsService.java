package cn.code.service;

import cn.code.dao.GoodsDao;
import cn.code.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luojiayu
 * @date 2021/4/14
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> getGoods() {
        return goodsDao.selectGoods();
    }
}
