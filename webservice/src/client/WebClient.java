package client;

import service.impl.WebServiceImpl;
import servicewsdl.impl.WebServiceImplService;

/**
 * @author luojiayu
 * @date 2021/5/11
 */
public class WebClient {
    public static void main(String[] args) {
        WebServiceImplService webServiceImplService = new WebServiceImplService();
        WebServiceImpl webService = webServiceImplService.getPort(WebServiceImpl.class);
        //获取查询方法，从portType的operation标签获取
        String weather=webService.queryWeather("北京");
        System.out.println(weather);
    }
}
