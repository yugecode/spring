package cn.code.dao;

import cn.code.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luojiayu
 * @date 2021/4/13
 */
@Repository
public interface GoodsDao {

    List<Goods> selectGoods();
}
