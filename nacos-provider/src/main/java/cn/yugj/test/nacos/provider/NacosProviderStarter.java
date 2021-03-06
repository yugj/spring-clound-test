package cn.yugj.test.nacos.provider;

import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yugj
 * @date 2020/6/29 3:44 下午.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderStarter {

    public static void main(String[] args) throws NacosException {
        SpringApplication.run(NacosProviderStarter.class, args);
    }

}
