package framework.core.registry;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {

    InetSocketAddress lookupService(String rpcServiceName);
}
