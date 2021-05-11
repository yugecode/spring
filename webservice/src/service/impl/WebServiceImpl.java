package service.impl;

import service.WebService;

/**
 * @author luojiayu
 * @date 2021/5/11
 */
@javax.jws.WebService
public class WebServiceImpl implements WebService {
    @Override
    public String queryWeather(String cityName) {
        return "good";
    }
}
