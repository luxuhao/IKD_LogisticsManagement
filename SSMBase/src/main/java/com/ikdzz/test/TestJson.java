package com.ikdzz.test;

import com.alibaba.fastjson.JSON;
import com.ikdzz.domain.shiro.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestJson {

    @Test
    public static void main(String[] args) {
        User student1 = new User();
        student1.setId(1);
        student1.setUsername("luxuhao");
       // student1.s(900);


        User student2 = new User();
        student2.setId(2);
        student2.setUsername("luxuhao888");
      //  student2.setMoney(90088);


        List<User> students = new ArrayList<User>();
        students.add(student1);
        students.add(student2);
        System.out.println("********JAVA对象列表转json字符串**********");
        String str1 = JSON.toJSONString(students);
        System.out.println(str1);
        System.out.println("********JAVA对象转json字符串**********");
        String str2 = JSON.toJSONString(student1);
        System.out.println(str2);

        System.out.println("********json字符串转JAVA对象列表**********");
        List<User> studentsTurn = JSON.parseArray(str1, User.class);
        studentsTurn.forEach(System.out::println);

        System.out.println("********json字符串转JAVA对象**********");
        User student4 = JSON.parseObject(str2, User.class);
        System.out.println(student4);
    }



}
