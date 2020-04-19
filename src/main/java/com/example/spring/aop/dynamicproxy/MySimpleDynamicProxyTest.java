package com.example.spring.aop.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理的简单实现
 * JDK动态代理通过反射来接收一个实现了接口的类，生成的动态代理类也是接口的子类.
 * 要求被代理的类必须实现一个接口
 * */
public class MySimpleDynamicProxyTest {

    public static void main(String[] args) {
        SimpleInterface simpleInterfaceProxy0 = createSimpleInterfaceProxy(new SimpleClass1());
        simpleInterfaceProxy0.doThing1();

        SimpleInterface simpleInterfaceProxy1 = createTProxy(new SimpleClass1());
        simpleInterfaceProxy1.doStringThing();

        SimpleInterface simpleInterfaceProxy2 = createTProxy(new SimpleClass2());
        simpleInterfaceProxy2.doStringThing();

        System.out.println(simpleInterfaceProxy1.doIntThing());
        System.out.println(simpleInterfaceProxy2.doIntThing());
    }

    public interface SimpleInterface{
        void doThing1();
        String doStringThing();
        int doIntThing();
    }

    public static class SimpleClass1 implements SimpleInterface{

        @Override
        public void doThing1() {
            System.out.println("SimpleClass1 doThing1");
        }

        @Override
        public String doStringThing() {
            System.out.println("SimpleClass1 doStringThing");
            return "SimpleClass1";
        }

        @Override
        public int doIntThing() {
            System.out.println("SimpleClass1 doIntThing");
            return 1;
        }
    }

    public static class SimpleClass2 implements SimpleInterface{

        @Override
        public void doThing1() {
            System.out.println("SimpleClass2 doThing1");
        }

        @Override
        public String doStringThing() {
            System.out.println("SimpleClass2 doStringThing");
            return "SimpleClass2";
        }

        @Override
        public int doIntThing() {
            System.out.println("SimpleClass2 doIntThing");
            return 2;
        }
    }

    private static SimpleInterface createSimpleInterfaceProxy(SimpleInterface object){
        return (SimpleInterface) Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("方法执行前。。");
                        method.invoke(object,args); //执行原方法，千万不可以用method.invoke(proxy, args)，自己调用自己会造成递归调用，无法退出 !!!!!!!
                        System.out.println("方法执行后。。");
                        return null;
                    }
                });
    }

    private static <T> T createTProxy(SimpleInterface object){
        Object o =  Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("方法执行前。。");
//                        method.invoke(object,args);
//                        System.out.println("方法执行后。。");
                        return method.invoke(object,args);
                    }
                });
        return (T) o;
    }

}
