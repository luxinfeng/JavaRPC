package framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RpcResponseCodeEnum {
    SUCCESS_CODE(200,"The remote call is successful"),
    ERROR_CODE(500, "The remote call is fail");

    private final int code;
    private final String message;
}
