package com.isoldier.basic;


import java.io.InputStream;

/**
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */
public class ClassLoaderDemo {


    public static void main(String[] args) throws Exception {

        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();

        classLoaderDemo.testClassLoader();
        classLoaderDemo.testUdClassLoader();


    }


    public  void testClassLoader() {

        println("------------------------------------------");
        println("1.类加载器的层级结构");
        // 打印java虚拟机的ClassLoader
        println(Thread.currentThread().getContextClassLoader());
        println(Thread.currentThread().getContextClassLoader().getParent());
        println(Thread.currentThread().getContextClassLoader().getParent().getParent());
    }

    public  void testUdClassLoader() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        println("------------------------------------------");
        System.out.println("------------------test2------------------------");
        // 自定义ClassLoader
        ClassLoader myLoader = new MyClassLoader();
        Class<?> clazz = myLoader.loadClass("com.isoldier.basic.ClassLoaderDemo");
        Object obj = clazz.newInstance();
        // 打印自定义ClassLoader加载的Class对象
        System.out.println(obj.getClass());
        // 打印被加载的Class对象是由哪个ClassLoader加载的
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(obj.getClass().getClassLoader().getParent().getParent());
    }





    static void println(Object object){
        System.out.println(object);
    }



    class MyClassLoader extends ClassLoader {

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            Class<?> result = null;
            try {
                result = this.findLoadedClass(name);

                if (result != null) {
                    return result;
                }
                String fileName = name.substring(name.lastIndexOf(".") + 1)
                        + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (Exception e) {
                throw new ClassNotFoundException(name);
            }
        }
    }

}
