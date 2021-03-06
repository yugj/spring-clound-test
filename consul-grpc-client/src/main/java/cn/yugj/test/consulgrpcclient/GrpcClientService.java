package cn.yugj.test.consulgrpcclient;

import cn.yugj.test.consulgrpcclient.stub.GreeterGrpc;
import cn.yugj.test.consulgrpcclient.stub.HelloWorldProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yugj
 * @date 2020/2/26 16:34.
 */
@Service
public class GrpcClientService {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * io.github.lognet grpc 没有提供负载均衡封装，直接使用DiscoveryClient 获取对应配置
     * 然后调用
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void sayHello() throws ExecutionException, InterruptedException {

        List<ServiceInstance> instances = discoveryClient.getInstances("grpc-consul-grpc");

        ServiceInstance serviceInstance = instances.get(0);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serviceInstance.getHost(), serviceInstance.getPort())
                .usePlaintext()
                .build();

        final GreeterGrpc.GreeterFutureStub greeterFutureStub = GreeterGrpc.newFutureStub(channel);
        final HelloWorldProto.HelloRequest helloRequest = HelloWorldProto.HelloRequest.newBuilder().setName("Bob").build();
        final String reply = greeterFutureStub.sayHello(helloRequest).get().getMessage();
        System.out.println(reply);
    }

}
