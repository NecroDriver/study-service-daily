package com.xin.daily.study;

/**
 * SingleTon 单例模式
 *
 * @author lemon 2020/5/21 14:30
 * @version 0.0.1
 **/
public class SingleTon {
    /**
     * 饿汉式 单例模式
     * 类在加载时就实例化，提供一个公共的方法获取实例化的类
     * <p> 优点：线程安全</p>
     * <p>缺点：类被加载时就实例化，有可能在整个代码周期都没有使用，且不会被回收会一直存在</p>
     */
    private static class Hunger {
        /**
         * 实例化自身
         */
        private static Hunger hunger = new Hunger();

        /**
         * 私有构造方法
         */
        private Hunger() {
        }

        /**
         * 获取自身实例化的类
         *
         * @return 实例
         */
        static Hunger getHunger() {
            return hunger;
        }
    }

    /**
     * 懒汉式 单例模式
     * 只有在真正使用的时候，才实例化
     * <p>优点：只有在真正使用的时候才实例化</p>
     * <p>缺点：线程不安全</p>
     */
    private static class Lazy{

        private static Lazy lazy;

        private Lazy(){}

        synchronized static Lazy getLazy(){
            if (null == lazy){
                lazy = new Lazy();
            }
            return lazy;
        }
    }

    /**
     * 枚举式 单例模式
     * 与饿汉式相似
     */
    private enum Enum{
        // 枚举就是单例
        Enum;
        public Enum getEnum(){
            return Enum;
        }
    }

    public static void main(String[] args) {
        Hunger hunger = Hunger.getHunger();
        if(hunger == Hunger.getHunger()){
            System.out.println("懒汉式--单例生效");
        }

        Lazy lazy = Lazy.getLazy();
        if(lazy == Lazy.getLazy()){
            System.out.println("饿汉式--单例生效");
        }

        if(Enum.Enum.getEnum() == Enum.Enum.getEnum()){
            System.out.println("枚举式--单例生效");
        }
    }
}
