package com.isoldier.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinmeng on 2018/7/18.
 * @version 1.0
 */
public class SimpleBeanUtils {

    public static void transBean2Map(Object obj,Map map) {

        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        }catch (Exception e){
            System.out.println("transBean2Map Error " + e);
        }
        return;
    }


    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

    }




    public static void main(String[] args){

        Student student = new Student();
        student.setAge(12);
        student.setName("nike");
        student.setBirthday(new Date());
        Map res = new HashMap();
        transBean2Map(student,res);
        System.out.println(res);
        Student student2= new Student();
        transMap2Bean(res,student2);
        System.out.println(student2);
    }




    static class Student{
        public String name;
        public Integer age;
        public Date birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}
