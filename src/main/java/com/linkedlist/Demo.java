package com.linkedlist;

import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode4);
        singleLinkedList.insert(heroNode1);
        singleLinkedList.insert(heroNode3);
        singleLinkedList.insert(heroNode2);
        singleLinkedList.insert(heroNode4);
        singleLinkedList.list();
        System.out.println();

        //更新节点
        HeroNode heroNode5 = new HeroNode(2, "haha", "nn");
        singleLinkedList.update(heroNode5);
        System.out.println("修改后的链表");
        singleLinkedList.list();
        System.out.println();

        //删除节点
        System.out.println("删除后的链表");
        singleLinkedList.remove(2);
        singleLinkedList.list();
        System.out.println();

        //返回有效节点的个数（不包括头结点）
        System.out.println("有效的节点个数"+getLength(singleLinkedList.getHead()));

        //倒数第K个节点
        HeroNode res=findLastIndexNode(singleLinkedList.getHead(),2);
        System.out.println(res);

        //反转单链表
        System.out.println("反转单链表：");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("\n逆序打印单链表");
        reversePrint(singleLinkedList.getHead());
    }

    //利用栈 倒序打印单链表
    public static void reversePrint(HeroNode head){
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur=head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur=cur.next;//将cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    public static void reverseList(HeroNode head){
        //如果链表为空或者只有一个节点则返回
        if (head.next==null ||head.next.next==null){
            return;
        }
        //定义一个辅助变量，帮助我们遍历原来的链表
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");
        //遍历原来的链表，遍历一个节点取出，放在新的链表
        while (cur!=null){
            //先暂时保存当前节点的下一个节点，因为后面需要用
            next=cur.next;//隐藏信息cur.next=next.next
            //将cur的下一个节点连接到新的链表的最前端
            cur.next=reverseHead.next;
            //将cur连接到新的链表
            reverseHead.next=cur;
            //让cur后移，即将cur变成cur.next -> 为了获得cur.next.next的值
            cur=next;
        }
        head.next=reverseHead.next;
    }
    /**
     * 获取到单链表的个数（不包括头结点）
     * @param head
     * @return
     */
    public static int getLength(HeroNode head){
        if (head.next==null){
            //列表为空
            return 0;
        }
        int length=0;
        //定义一个辅助变量
        HeroNode cur=head.next;
        //遍历获取length的值即节点个数
        while (cur != null) {
            length++;
            cur=cur.next;
        }
        return length;
    }

    /**
     * 编写一个方法，接收head节点同时接收一个index
     * index，表示倒数第index个节点
     * 先把链表遍历，得到链表长度，getLength
     * 得到size后，我们从链表的第一个开始遍历到(size-index)个，就可以得到
     * 如果找到了返回该节点，否则返回null
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度（节点个数）
        int size=getLength(head);
        //第二次遍历 size-index 位置，就是我们的倒数第K个位置
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量，for循环定位到倒数的index
        HeroNode cur=head.next;
        for (int i = 0; i < size - index; i++) {
            cur=cur.next;
        }
        return cur;
    }
}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){
        //因为head 节点不动，需要一个辅助节点
        HeroNode temp=head;
        //遍历链表，找到最后的节点，当节点的next为Null则这个节点为最后节点
        while (true){
            if (temp.next==null){
                break;
            }
            //如果没有找到就将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //把节点的数据赋值给最后一个节点temp.next
        temp.next=heroNode;
    }

    /**
     * 插入节点
     * @param heroNode
     */
    public void insert(HeroNode heroNode){
        //因为head 节点不动，需要一个辅助节点
        //我们的temp是位于添加位置的前一个节点
        HeroNode temp=head;
        boolean flag=false; //标志添加的编号是否存在，默认为false
        while (true){
            if (temp.next==null){
                //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no>heroNode.no){
                //位置找到，在temp的后面插入
                break;
            }else if (temp.next.no==heroNode.no){
                //说明添加位置的编号已经有数据了
                flag=true;
                break;
            }
            //后移一个 继续遍历
            temp=temp.next;
        }
        //判断flag的值
        if (flag){
            //不能添加，说明编号存在
            System.out.println("准备插入的英雄编号 "+heroNode.no+" 已经存在");
        }else {
            //加入到链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 删除节点
     * 1.head不能动，因此我们需要一个temp辅助节点找到待删除的前一个节点
     * 2.说明我们在比较时，是temp.next.no 和需要删除的节点的no比较
     * @param no
     */
    public void remove(int no){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if (temp.next==null){
//                遍历到链表末
                break;
            }
            /**
             * 因为是找到删除节点的前一个节点
             * 所以前一个节点的next指向被删除节点
             */
            if (temp.next.no==no){
                //找到删除节点
                flag=true;
                break;
            }
            //继续遍历
            temp=temp.next;
        }
        //判断flag
        if (flag){
            //找到节点
            //将被删除节点上一个节点的next的值修改为被删除节点的next
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

    /**
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示找到该节点
        while (true){
            if (temp==null){
                break;//表示遍历完链表
            }
            if (temp.no==heroNode.no){
                //找到目标节点,退出遍历
                flag=true;
                break;
            }
            //继续循环遍历
            temp=temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag){
            temp.name=heroNode.name;
            temp.nickname=heroNode.nickname;
        }else {
            //没有找到目标的no
            System.out.printf("没有找到编号%d的节点，不能修改\n",heroNode.no);
        }
    }



    //显示链表
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助节点遍历
        //因为头节点没有数据所以从第二个节点开始即head.next
        HeroNode temp=head.next;
        while (true){
            //判断是否到链表最后
            if (temp==null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //temp后移
            temp=temp.next;
        }
    }
}


//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
