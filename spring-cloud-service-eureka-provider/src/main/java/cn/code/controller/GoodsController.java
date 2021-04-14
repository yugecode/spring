package cn.code.controller;

import cn.code.common.response.ResponseTemplate;
import cn.code.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojiayu
 * @date 2021/4/14
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/provider/get")
    public ResponseTemplate getGoods() {
        return ResponseTemplate.ok(goodsService.getGoods());
    }
}
