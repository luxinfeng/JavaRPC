package framework.common.entity;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class RpcServiceProperties {
    private String version;
    private String group;
    private String serviceName;

    public String getRpcServiceName(){
        return this.version + this.group + this.serviceName;
    }
}
