package com.tree.threadBinary;

public class ThreadedBinaryDemo {
    public static void main(String[] args) {
        //测试
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"keen");
        HeroNode node5 = new HeroNode(10,"ywj");
        HeroNode node6 = new HeroNode(14,"cly");

        //二叉树递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.ThreadedBinaryTree();

        //获取10号结点的前驱结点
        HeroNode leftNode=node5.getLeft();
        System.out.println("10号结点的前驱结点:"+leftNode);
        //获取10号结点的后继结点
        HeroNode rightNode=node5.getRight();
        System.out.println("10号结点的后继结点:"+rightNode);

        //当线索化二叉树后,不能用原先方法遍历方法
        threadedBinaryTree.threadedList();
    }
}

//定义ThreadedBinaryTree
class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载方法
    public void ThreadedBinaryTree(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList(){
        //定义一个辅助变量,存放当前遍历的结点,从root开始
        HeroNode node=root;
        while (node!=null){
            //循环找到leftType=1的结点 即第一个找到8结点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的就是后继结点,就一直输出
            while (node.getRightType()==1){
                //获取当前结点的后继结点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node=node.getRight();
        }

    }


    //编写线索化的方法
    public void threadedNodes(HeroNode node) {
        //如果node==null 不能线索化
        if (node == null) {
            return;
        }

        //先线索化左子树
        threadedNodes(node.getLeft());

        //线索化当前结点
        //处理当前结点的前驱结点
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);

        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!  每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre=node;

        //线索化右子树
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树,不能删除");
        }
    }
}

//创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //如果leftType==0 表示指向的是左子树, 如果是1 表示指向前驱结点
    //如果rightType==0 表示指向的是右子树, 如果是1 表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        //输出父节点
        System.out.println(this);

        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        //比较当前结点
        if (this.no == no) {
            return this;
        }
        //判断当前结点的左子节点是否为空, 不为空,则递归前序查找
        // 辅助变量,用来重置辅助变量和返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//找到
            return resNode;
        }
        //判断当前结点的左子节点是否为空, 不为空,则递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空, 不为空,则递归中序查找
        // 辅助变量,用来重置辅助变量和返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {//找到
            return resNode;
        }

        if (this.no == no) {
            return this;
        }
        ////判断当前结点的右子节点是否为空, 不为空,则递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判断当前结点的左子节点是否为空, 不为空,则递归后序查找
        // 辅助变量,用来重置辅助变量和返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//找到
            return resNode;
        }
        //判断当前结点的右子节点是否为空, 不为空,则递归后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        //如果左右子树都没找到比较当前结点
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //删除结点
    public void delNode(int no) {

        //如果当前结点的左子节点是删除结点,
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //如果当前结点的右子节点是删除结点,
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //当前结点的左右子结点都不是删除结点,递归左子树
        if (this.left != null) {
            this.left.delNode(no);
        }
        //当前结点的右右子结点都不是删除结点,递归左子树
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
