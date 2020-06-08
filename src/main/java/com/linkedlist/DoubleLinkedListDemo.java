package com.linkedlist;

public class DoubleLinkedListDemo {
    public DoubleLinkedListDemo() {
    }

    public static void main(String[] args) {
        System.out.println("双向链表：");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        //修改
        HeroNode2 newHero =new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHero);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        System.out.println();
        doubleLinkedList.remove(4);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    DoubleLinkedList() {
    }
    //先初始化一个头结点
    private HeroNode2 head=new HeroNode2(0,"","");
    //返回头结点
    public HeroNode2 getHead() {
        return this.head;
    }

    //显示链表
    public void list(){
        //判断链表是否为空
        if (this.head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助节点遍历
        //因为头节点没有数据所以从第二个节点开始即head.next
        HeroNode2 temp=head.next;
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

    //添加节点(最后)
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode2 heroNode){
        //因为head 节点不动，需要一个辅助节点
        HeroNode2 temp=head;
        //遍历链表，找到最后的节点，当节点的next为Null则这个节点为最后节点
        while (true){
            if (temp.next==null){
                break;
            }
            //如果没有找到就将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp=head.next;
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

    /**
     * 从双向链表中删除一个节点
     * @param no
     */public void del(int no) {
        if (this.head.next == null) {
            System.out.println("链表为空，无法删除");
        } else {
            HeroNode2 temp = this.head.next;

            boolean flag;
            for(flag = false; temp != null; temp = temp.next) {
                if (temp.no == no) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }

        }
    }
    public void remove(int no){
        //判断当前链表是否为空
        if (this.head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp=this.head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                //遍历到链表末
                break;
            }
            /**
             * 因为是找到删除节点的前一个节点
             * 所以前一个节点的next指向被删除节点
             */
            if (temp.no==no){
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
            temp.pre.next = temp.next;
            //判断是否为最后一个节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }


}



//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
