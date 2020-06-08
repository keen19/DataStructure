package com.linkedlist.test;

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    DoubleLinkedList() {
    }

    public HeroNode2 getHead() {
        return this.head;
    }

    public void list() {
        if (this.head.next == null) {
            System.out.println("链表为空");
        } else {
            for(HeroNode2 temp = this.head.next; temp != null; temp = temp.next) {
                System.out.println(temp);
            }

        }
    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp;
        for(temp = this.head; temp.next != null; temp = temp.next) {
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 newHeroNode) {
        if (this.head.next == null) {
            System.out.println("链表为空~");
        } else {
            HeroNode2 temp = this.head.next;

            boolean flag;
            for(flag = false; temp != null; temp = temp.next) {
                if (temp.no == newHeroNode.no) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
            } else {
                System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
            }

        }
    }

    public void del(int no) {
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
}
