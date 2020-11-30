package com.imooc.demo.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredToken {

    /**
     * 是否需要权限拦截 默认为true
     */
    boolean permissionCheck() default true;

    /**
     * 是否是页面菜单，默认是“Y”，为“N”做接口拦截
     * @return
     */
    String meuPge() default "Y";
}
