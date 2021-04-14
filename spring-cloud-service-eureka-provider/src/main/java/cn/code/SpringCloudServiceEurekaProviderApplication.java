package cn.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "cn.code.**.dao")
public class SpringCloudServiceEurekaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceEurekaProviderApplication.class, args);
    }

}
