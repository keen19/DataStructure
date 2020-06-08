package com.queue;

import com.sun.deploy.panel.ITreeNode;

import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println("测试数组");
        //取4但是有效数据为3
        CircleArray queue=new CircleArray(4);
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
class CircleArray{
    //表示数组的最大容量
    private int maxSize;
    //front 变量的含义做一个调整，front就指向队列的第一个元素，也就是说arr[front]
    //front 的初始值为0
    private int front;
    //rear 变量的含义做一个调整，rear就指向队列的最后一个元素的后一个位置，
    //rear 的初始值为0
    private int rear;
    private int[] arr;

    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];

    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
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
        //直接将数据加入
        arr[rear]=n;
        rear=(rear+1)%maxSize;//让rear后移
    }
    //获取队列的数据，出队列
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移
        //3.将临时保存的变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列的所有数据
    public void show(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空，无数据");
        }
        //从front开始遍历，遍历多少个元素
        //
        for (int i = front; i < front+size(); i++) {
            //两种输出方式，第一种是c语言，第二种是JAVA
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);

            //System.out.println("arr["+i+"]="+arr[i]);
        }
    }
    //求出当前队列的有效数据的个数
    public int size(){
        return (rear+maxSize-front) %maxSize;
    }
    //显示队列的头数据，注意不是去出数据
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }
}
