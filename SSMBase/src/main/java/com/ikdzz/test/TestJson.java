package com.ikdzz.test;

import com.alibaba.fastjson.JSON;
import com.ikdzz.domain.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestJson {

    @Test
    public static void main(String[] args) {
        Account student1 = new Account();
        student1.setId(1);
        student1.setName("luxuhao");
        student1.setMoney(900);


        Account student2 = new Account();
        student2.setId(2);
        student2.setName("luxuhao888");
        student2.setMoney(90088);


        List<Account> students = new ArrayList<Account>();
        students.add(student1);
        students.add(student2);
        System.out.println("********JAVA对象列表转json字符串**********");
        String str1 = JSON.toJSONString(students);
        System.out.println(str1);
        System.out.println("********JAVA对象转json字符串**********");
        String str2 = JSON.toJSONString(student1);
        System.out.println(str2);

        System.out.println("********json字符串转JAVA对象列表**********");
        List<Account> studentsTurn = JSON.parseArray(str1, Account.class);
        studentsTurn.forEach(System.out::println);

        System.out.println("********json字符串转JAVA对象**********");
        Account student4 = JSON.parseObject(str2,Account.class);
        System.out.println(student4);
    }



}
