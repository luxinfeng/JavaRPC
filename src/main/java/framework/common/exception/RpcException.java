package framework.common.exception;

public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum serviceCanNotBeFound, String detail){
        super(serviceCanNotBeFound.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause){
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum){
        super(rpcErrorMessageEnum.getMessage());
    }
}
