package framework.core.provider;

import framework.common.entity.RpcServiceProperties;

public interface ServiceProvider {

    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);

    Object getService(RpcServiceProperties rpcServiceProperties);

    void publishService(Object object, RpcServiceProperties rpcServiceProperties);

    void publishService(Object object);
}
