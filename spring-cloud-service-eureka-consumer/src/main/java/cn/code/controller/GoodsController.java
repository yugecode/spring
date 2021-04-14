package cn.code.controller;

import cn.code.common.response.ResponseTemplate;
import cn.code.service.GoodsService;
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
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/consumer/get")
    public ResponseTemplate<String> getGoods() {
        return ResponseTemplate.ok(goodsService.getGoods());
    }
}
