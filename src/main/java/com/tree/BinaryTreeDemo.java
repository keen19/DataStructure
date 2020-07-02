package com.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //添加结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊");
        HeroNode node4 = new HeroNode(4, "林冲");

        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);

        //前序遍历 1, 2, 3, 4
        System.out.println("前序遍历:");
        binaryTree.preOrder();

        //中序遍历 2 , 1 , 3 ,4
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        //后序遍历 2 ,4 ,3 ,1
        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序遍历
        System.out.println("前序查找:");
        HeroNode result = binaryTree.preOrderSearch(4);
        if (result != null) {
            System.out.println("name:" + result.getName() + ",  no:" + result.getNo());
        } else {
            System.out.println("没有找到");
        }
        binaryTree.delNode(3);
        binaryTree.preOrder();
    }
}

//定义BinaryTree
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public void delNode(int no ){
        if (root!=null){
            if (root.getNo()==no){
                root=null;
            }else {
                root.delNode(no);
            }
        }else {
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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
        if (this.right!=null&&this.right.no==no){
            this.right=null;
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
