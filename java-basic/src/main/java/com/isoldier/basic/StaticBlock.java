package com.isoldier.basic;

/** 代码块的执行顺序
 * 静态代码块->普通代码块 ->无参构造函数
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */
public class StaticBlock {

    public static void main(String[] args){
        System.out.println("order: ------");
        new StaticCodeDemo();
    }

    static class StaticCodeDemo{

        StaticCodeDemo(){
            System.out.println("3.无参构造函数");
        }
        static{
            System.out.println("1.静态代码块");
        }
        {
            System.out.println("2.普通代码块");
        }

    }

}
