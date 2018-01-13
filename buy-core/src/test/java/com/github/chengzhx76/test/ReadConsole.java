package com.github.chengzhx76.test;

import java.util.Scanner;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class ReadConsole {
    public static void main(String[] args) {
        System.out.print("输入");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("输入数据："+read);
    }
}
