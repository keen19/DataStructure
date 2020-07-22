package com.BinarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加结点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        //删除叶子结点
        System.out.println("删除后:");
        binarySortTree.delNode(3);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加结点
    public void add(Node node) {
        if (root == null) {
            //如果root为空,把node放到root
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("数组为空");
        }
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除的结点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //返回右子树的最小结点
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        //把最小结点删除
        delNode(target.value);
        //把最小结点的值返回
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //辅助结点 , 先找到要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果是只有一个结点,即只有root结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                //把获得的最小值替换掉当前的结点
                targetNode.value = minVal;
            } else {
                //删除只有一颗子树的结点
                //如果删除左子结点
                if (parent != null) {
                    if (targetNode.left != null) {
                        //如果当前结点是父结点的左子结点
                        if (parent.left.value == value) {
                            //把当前结点的左子结点的值替换给当前结点的父结点
                            parent.left = targetNode.left;
                        } else {
                            //如果当前结点是父结点的右子结点
                            //把当前结点的左子结点的值替换掉当前结点的父结点的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    //如果删除右子结点
                    if (parent != null) {
                        //如果当前结点是父结点的左子结点
                        if (parent.left.value == value) {
                            //把当前结点的左子结点的值替换给当前结点的父结点
                            parent.left = targetNode.right;
                        } else {
                            //如果当前结点是父结点的右子结点
                            //把当前结点的左子结点的值替换掉当前结点的父结点的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;


    //查找要删除的结点
    public Node search(int value) {
        if (this.value == value) {
            //当前结点就是查找的结点
            return this;
        } else if (value < this.value) {
            //如果当前结点的值大于要查找的值,向左子树递归
            if (this.left == null) {
                //如果当前结点的左子结点为空,说明没有找到
                return null;
            }
            return this.left.search(value);
        } else {
            //如果查找的值大于当前结点的值,向右子树递归
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            //如果当前结点的左子结点不为空,并且左子结点的值等于要查找的值,返回当前结点
            //反之
            return this;
        } else {
            //如果查找的值小于当前结点的值,并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归查找
                return this.right.searchParent(value);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    public Node(int value) {
        this.value = value;
    }

    //添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //如果当前结点的值大于传入结点,将传入结点放入当前结点的左子树
        if (node.value < this.value) {
            //如果当前结点的左子结点为NULL
            if (this.left == null) {
                this.left = node;
            } else {
                //否则递归
                this.left.add(node);
            }
        } else {
            //否则,如果当前结点的值小于传入结点,将传入结点放入当前结点的右子树
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

