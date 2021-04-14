package cn.code.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author luojiayu
 * @date 2021/4/14
 */
@Service
public class GoodsService {

    private final RestTemplate restTemplate;

    public GoodsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGoods() {
        return restTemplate.getForEntity("http://spring-cloud-service-eureka-provider/goods/provider/get",
            String.class).getBody();
    }
}
