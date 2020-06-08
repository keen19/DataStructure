package com.linkedlist;

public class Josepfu {
    public static void main(String[] args) {

        //遍历环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.show();

        //测试出圈方法
        circleSingleLinkedList.countBoy(10,20,125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first;

    public void countBoy(int startNo,int countNum,int num){
        //对数据进行校验
        if (first == null || startNo < 1 || startNo > num) {
            System.out.println("参数输入有误.请重新输入");
            return;
        }
        //创建要给辅助指针，帮助小孩完成出圈
        Boy helper=first;
        //出圈开始前把辅助指针指向链表最后节点
        while (true) {
            if (helper.next==first){
                break;
            }
            helper=helper.next;
        }

        //小孩报数，先让first和helper移动startNo-1次
        for (int i = 0; i < startNo-1; i++) {
            helper=first;
            first=first.next;
        }

        //当小孩报数，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时的移动countNum-1次
            for (int i = 0; i < countNum - 1; i++) {
                helper=first;
                first = first.next;
            }
            //这时first指向出圈的节点
            System.out.printf("小孩%d出圈\n", first.no);
            first=first.next;
            helper.next=first;
            }
        System.out.println("最后留在圈中的小孩编号："+first.no);
    }

    //添加小孩节点
    public void addBoy(int num) {
        //num 做一个数据校验
        if (num < 2) {
            System.out.println("数量太少");
            return;
        }
        //创建辅助指针
        Boy curBoy=null;
        //使用for循环来创建环形链表
        for (int i = 1; i <=num; i++) {
            //根据编号，创建小孩节点
            Boy boy=new Boy(i);
            //如果是第一个小孩
            if (i==1){
                first=boy;
                //构成环
                first.next = boy;
                //让boy指向第一个小孩
                curBoy=first;
            }else {
                curBoy.next=boy;
                boy.next=first;
                curBoy=boy;
            }

        }
    }

    //遍历当前的环形链表
    public  void show(){
        //判断链表是否为空
        if (first==null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.no);
            if (curBoy.next==first){
                //说明遍历完毕
                break;
            }
            curBoy=curBoy.next;
        }
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    //编号
    public int no;
    //指向下一个节点
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
