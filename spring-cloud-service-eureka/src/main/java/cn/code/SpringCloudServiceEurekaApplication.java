package cn.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudServiceEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceEurekaApplication.class, args);
    }

}
