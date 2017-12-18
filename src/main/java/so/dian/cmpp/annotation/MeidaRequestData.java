package so.dian.cmpp.annotation;

import java.lang.annotation.*;

/**
 * 标识请求中入参对应的变量
 *
 * @author will
 * @since 2016-08-13
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MeidaRequestData {

}
