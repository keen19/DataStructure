package com.queue;

import sun.reflect.generics.tree.VoidDescriptor;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        ArrayQueue queue=new ArrayQueue(4);
//        arrayQueue.addQueue(2);
//        arrayQueue.addQueue(6);
//        arrayQueue.addQueue(4);
//        arrayQueue.addQueue(5);
//        arrayQueue.show();
        //接收用户输入
        char key=' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头");
            key=scanner.next().charAt(0);//接收第一个字符
            switch (key){
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的 数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://队列头
                    try {
                        int res=queue.headQueue();
                        System.out.printf("队列头数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                        scanner.close();
                        loop=false;
                        break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class ArrayQueue{
    //数组最大容量
    private int maxSize;
    //队列头
    private int rear;
    //队列尾
    private int front;
    //该数据用于存放数据，模拟队列
    private int[] arr;

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        //指向队列头，分析出font是指向队列头的前一个位置
        front=-1;
        //指向队列尾，分析出rear是指向队尾的数据
        rear=-1;
        arr=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //插入数据到队列
    public void addQueue(int n){
        //判断是否为满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;//让rear后移
        arr[rear]=n;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void show(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空，无数据");
        }
        for (int i = 0; i < arr.length; i++) {
            //两种输出方式，第一种是c语言，第二种是JAVA
            System.out.printf("arr[%d]=%d\n",i,arr[i]);

            //System.out.println("arr["+i+"]="+arr[i]);
        }
    }

    //显示队列的头数据，注意不是去出数据
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front+1];
    }
}
