package com.buaa.review.java.injection;

import com.buaa.review.java.injection.F;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class S {

    F f;

    S(@Lazy F f){
        this.f = f;
    }

    /**
     * Spring构造器注入循环依赖的解决方案是@Lazy，
     * 其基本思路是：对于强依赖的对象，一开始并不注入对象本身，
     * 而是注入其代理对象，以便顺利完成实例的构造，形成一个完成的对象，
     * 这样与其它应用层对象就不会形成互相依赖的关系；当需要调用真实对象的方法时，
     * 通过TargetSource去拿到真实的对象[DefaultListableBeanFactory#doResolveDependency]，
     * 然后通过反射完成调用
     *
     * Setter注入或者Field注入
     */

}
