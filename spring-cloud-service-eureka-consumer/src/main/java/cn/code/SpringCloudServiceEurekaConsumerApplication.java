package cn.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudServiceEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceEurekaConsumerApplication.class, args);
    }

}
