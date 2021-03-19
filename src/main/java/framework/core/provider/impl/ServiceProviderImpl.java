package framework.core.provider.impl;

import framework.common.entity.RpcServiceProperties;
import framework.common.exception.RpcErrorMessageEnum;
import framework.common.exception.RpcException;
import framework.core.provider.ServiceProvider;

import javax.imageio.spi.ServiceRegistry;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceProviderImpl implements ServiceProvider {
    // 用来存放对应的服务
    private final Map<String, Object> serviceMap;
    // 用来注册对应的服务
    private final ServiceRegistry serviceRegistry;

    public ServiceProviderImpl(){
        serviceMap = new ConcurrentHashMap<>();
        serviceRegistry = new ServiceRegistry();
    }
    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {
        String rpcServiceName = rpcServiceProperties.getRpcServiceName();
        if(serviceMap.containsKey(rpcServiceName)){
            return;
        }
        serviceMap.put(rpcServiceName, service);
        log.info("Add service: {} and interfaces:{}", rpcServiceName, service.getClass().getInterfaces());
    }

    @Override
    public Object getService(RpcServiceProperties rpcServiceProperties) {
        Object service = serviceMap.get(rpcServiceProperties.getRpcServiceName());
        if(service == null){
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND);
        }
        return service;
    }

    @Override
    public void publishService(Object object, RpcServiceProperties rpcServiceProperties) {
        try{
            String host = InetAddress.getLocalHost().getHostAddress();
            Class<?> serviceRelatedInterface = object.getClass().getInterfaces()[0];
            String serviceName = serviceRelatedInterface.getCanonicalName();
            rpcServiceProperties.setServiceName(serviceName);
            this.addService(object, serviceRelatedInterface, rpcServiceProperties);
            serviceRegistry.registerServiceProviders(rpcServiceProperties.getRpcServiceName(), new InetSocketAddress(host, NettyRpcServer.PORT));
        } catch (UnknownHostException e) {
            log.error("occur exception when getHostAddress", e);
        }
    }

    @Override
    public void publishService(Object object) {
        this.publishService(object, RpcServiceProperties.builder().group("").version("").build());
    }
}
