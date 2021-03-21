package framework.core.loadBalance;

import framework.common.extension.SPI;

import java.util.List;

@SPI
public interface LoadBalance {
    String selectServiceAddress(List<String> serviceAddress, String rpcServiceName);
}
