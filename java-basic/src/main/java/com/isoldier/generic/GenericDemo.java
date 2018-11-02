package com.isoldier.generic;

/**
 * @author jinmeng on 2018/9/18.
 * @version 1.0
 */
public class GenericDemo {

    //Lev 1
    static class Food{}

    //Lev 2
    static class Fruit extends Food{}
    static class Meat extends Food{}

    //Lev 3
    static class Apple extends Fruit{}
    static class Banana extends Fruit{}
    static class Pork extends Meat{}
    static class Beef extends Meat{}

    //Lev 4
    static class RedApple extends Apple{}
    static class GreenApple extends Apple{}

    static class Plate<T>{
        private T item;
        public Plate(T t){item=t;}
        public void set(T t){item=t;}
        public T get(){return item;}
    }



    public static void main(String[] args){

        Plate<? super Fruit> p=new Plate<Fruit>(new Fruit());

        //存入正常
        p.set(new Fruit()); //Error
        p.set(new Apple()); //Error
//        p.set(new Object()); //Error

        //读取出来的东西只能存放在Object类里。
//        Apple newFruit3=p.get();    //Error
//        Fruit newFruit1=p.get();    //Error
//        Object newFruit2=p.get();
    }


}
