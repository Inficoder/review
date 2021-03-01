package com.buaa.review.designPattern.singleton;

public class LazySingleton {

    /**
     * 懒汉式
     * 类加载时没有实例，需要调用一次getInstance方法
     */

    //private 避免类在外部被实例化
    private LazySingleton() { }

    private static volatile LazySingleton instance = null;

    public static LazySingleton getInstance(){
        if(instance == null){
            /**
             *
             * @问题一
             * instance = new LazySingleton();
             * 此行代码，会发生如下三步
             * 1.给instance分配空间
             * 2.调用LazySingleton的构造函数来初始化
             * 3.将instance对象指向分配的内存空间
             *
             * JVM存在指令重排机制，可能会导致执行顺序改变 1-2-3 -> 1-3-2
             * 在多线程情况下，发生指令重排，线程1执行了1,然后执行了3
             * 此时线程2抢占到cpu，执行了getInstance，此时线程2拿到了线程1生成的instance（未被构造函数初始化），报错！
             *
             * 此时，需要volatile关键字 去修饰instance，保证instance的操作被所有线程看到
             *
             * @问题二
             * 使用synchronized修饰此行代码
             * 线程1与线程2同时判断instance==null 都会进行new LazySingleton()
             * 所以需要加入同步方法 避免多实例
             * 不需要再方法上加synchronized，只需要在new LazySingleton()时修饰
             */
            synchronized (LazySingleton.class){
                instance = new LazySingleton();
            }
        }
        return instance;
    }
}
