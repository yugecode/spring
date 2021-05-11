package service;

import service.impl.WebServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author luojiayu
 * @date 2021/5/11
 */
public class WebServer {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WebServiceImpl());
    }
}
