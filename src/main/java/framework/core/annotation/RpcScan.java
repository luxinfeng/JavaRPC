package framework.core.annotation;

import framework.core.Spring.CustomScannerRegister;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomScannerRegister.class)
@Documented
public @interface RpcScan {
    String[] baseRackage();
}
