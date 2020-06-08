package com.Stack;

import java.util.Scanner;
import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key="";
        Scanner scanner = new Scanner(System.in);
        //控制是否退出菜单
        boolean loop = true;

        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:出栈");
            System.out.println("请选择你的操作");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                case "push":
                    System.out.println("请输出一个数");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res=stack.pop();
                        System.out.println("出栈数据是"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }

    }
}
class ArrayStack{
    //栈的大小
    private int maxSize;
    //数组，数组模拟栈，数据放在数组
    private int[] stack;
    //top 表示栈顶，初始化为-1
    private int top=-1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top==-1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈pop
    public int pop(){
        //判断栈是否为空
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //显示栈的情况
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
