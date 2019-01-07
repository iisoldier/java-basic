package com.isoldier.basic;

import com.isoldier.basic.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jinmeng on 2018/11/2.
 * @version 1.0
 */
public class LambdaDemo {



    static Map listToMap( List<Student> studentList){

        Map<Integer, Student> brandDtoMap =studentList.stream()
                .collect(
                        Collectors.toMap(
                                Student::getId
                                , Function.identity()
                        )
                );

        return brandDtoMap;
    }


    public static void main(String[] args){
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("jinmeng");
        studentList.add(student);
        Map result = listToMap(studentList);


        System.out.println(result);

        System.out.println(studentList.stream().map(e -> e.getId()).collect(Collectors.toList()));
        studentList.stream().forEach(System.out::println);





        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        StringBuilder b = new StringBuilder();
        list.forEach(b::append);

        String s = list.stream().map(e -> e.toString()).reduce("", String::concat);
        String ss = list.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(b);
        System.out.println(s);
        System.out.println(ss);
    }


}
