package com.isoldier.basic;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时参数 -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * @author jinmeng on 2019/1/17.
 * @version 1.0
 */
public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        BigOMObject strongRef = new BigOMObject();

        WeakReference weakRef = new WeakReference<BigOMObject>(strongRef);

        if(strongRef.equals(weakRef.get())){
            System.out.println("same object");
        }
        strongRef = null;
        System.gc();
        System.gc();
        System.gc();
        List<Object> list = new ArrayList();
        while (true){
            System.out.println(weakRef.get());
            list.add(new OMObject());
            if(weakRef.get() == null){
                System.out.println("!!!!!!!!!now gc has happened!!!!!!!!!");
                break;
            }
        }
    }

    public static class OMObject {

        private byte[] OM_OBJECT;
        public OMObject() {
            this.OM_OBJECT = new byte[1024 * 1024];
        }
    }

    public static class BigOMObject {

        private byte[] OM_OBJECT;
        public BigOMObject() {
            this.OM_OBJECT = new byte[1024 * 1024 * 3];
        }
    }
}
