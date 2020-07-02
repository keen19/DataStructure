package com.search;

import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建员工
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输出查找的id:");
                    id=scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "delete":
                    System.out.println("请输出要删除的id:");
                    id=scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTable 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    //链表数
    private int size;


    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //删除员工
    public void delete(int id){
        //使用散列函数找到对应链表
        int empLinkedListNO =hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].delete(id);
        if (emp==null){
            System.out.println("没有该员工");
        }else {

        }

    }

    //添加员工
    public void add(Emp emp) {
        //根据员工的id,得到员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有链表,遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    public void find(int id){
        //使用散列函数找到对应链表
        int empLinkedListNO =hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpByid(id);
        if (emp!=null){
            System.out.println("在第"+(empLinkedListNO+1)
                    +"条链表找到 员工 [ id ="+emp.id+",name="+emp.name+" ]");
        }else {
            System.out.println("没有该员工");
        }
    }

    public int hashFun(int id) {
        return id % size;
    }
}

//表示员工
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList 表示链表
class EmpLinkedList {
    //头指针,执行第一个Emp,因此我们这个链表的head 是直接指向第一个Emp
    private Emp head;

    public void add(Emp emp) {
        //如果添加第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个员工则使用辅助指针
        Emp tmp = head;
        while (true) {
            if (tmp.next == null) {
                //链表最后找到,退出循环
                break;
            }
            //辅助指针往后移
            tmp = tmp.next;
        }
        //将emp加入链表
        tmp.next = emp;
    }

    //遍历链表的员工信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (++no) + "链表为空");
            return;
        }
        System.out.println("第" + (no + 1) + "链表的信息为:");
        //辅助指针
        Emp tmp = head;
        while (true) {
            System.out.println(" [ id=" + tmp.id + " name=" + tmp.name + " ]");
            if (tmp.next == null) {
                //链表最后找到,退出循环
                break;
            }
            //辅助指针往后移
            tmp = tmp.next;
        }
    }

    //删除员工
    public Emp delete(int id){
        //判断链表是否为空
        if (head==null){
            System.out.println("链表为空");
            return null;
        }

        //判断是否为头节点
        if (head.id==id){
            head=head.next;
            return null;
        }

        //辅助指针
        Emp tmp=head;

        while (true){
            //找到目标前一个位置
            if (tmp.next.id==id){
                tmp.next=tmp.next.next;
                break;
            }
            //没找到
            if (tmp.next==null){
                tmp=null;
                break;
            }
            tmp=tmp.next;
        }

        return tmp;
    }

    //查找员工
    public Emp findEmpByid(int id){
        //判断链表是否为空
        if (head==null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp tmp=head;
        while (true){
            //找到
            if (tmp.id==id){
                break;
            }
            //没找到
            if (tmp.next==null){
                tmp=null;
                break;
            }
            tmp=tmp.next;
        }
        return tmp;
    }
}