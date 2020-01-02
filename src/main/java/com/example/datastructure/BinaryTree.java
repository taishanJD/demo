package com.example.datastructure;


/**
* 二叉树的实现
* */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    //查找某个节点
    public Node find(int data){
        Node current = root;

        if (current == null){
            return null;
        }
        while(current.data != data){
            if (data < current.data){
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null){
                return null;
            }
        }
        return current;
    }

    /**
     * 插入节点
     * */
    public void insert(int data){
        Node newNode = new Node(data);
        if (root == null){
            root = newNode;
        }else {
            Node current = root;
            Node parent;
            while (true){
                parent = current;
                if (data < parent.data){
                    current = current.leftChild;
                    if (current == null){
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    //删除节点
    public void delete(int data){

    }

    //前序遍历
    public void preOrder(Node root){
        if (root != null){
            root.display();
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    //中序遍历
    public void midOrder(Node root){
        if (root != null){
            midOrder(root.leftChild);
            root.display();
            midOrder(root.rightChild);
        }
    }

    //后序遍历
    public void postOrder(Node root){
        if (root != null){
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            root.display();
        }
    }

    /**
     * 二叉搜索树转双向链表
     * 二叉搜索树的特性是，其中序遍历是有序的，同时左子树的元素都比根结点小，右子树的元素都比根节点大。
     * 由上述特性可知，把二叉搜索树转换成双向链表后，根节点的前一个元素是左子树的最右边结点，根结点的后一个元素是右子树的最左边结点。
     * 左子树和右子树的转换过程是一样的，所以是一个递归的过程。
     * 注意，转换后返回的是指向双向链表的头指针。
     */
    public Node convertToDL(Node root){
        if (root == null)
            return null;
        if (root.leftChild != null){
            Node left = convertToDL(root.leftChild);
            while (left.rightChild != null){
                left = left.rightChild;
            }
            left.rightChild = root;
            root.leftChild = left;
        }

        if (root.rightChild != null){
            Node right = convertToDL(root.rightChild);
            while (right.leftChild!=null){
                right = right.leftChild;
            }
            root.rightChild = right;
            right.leftChild = root;
        }

        while (root.leftChild!=null){
            root = root.leftChild;
        }

        return root;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(12);
        tree.insert(10);
        tree.insert(19);
        tree.insert(11);
        tree.insert(9);
        tree.insert(15);
        tree.insert(22);
//        tree.preOrder(tree.root);
//        tree.midOrder(tree.root);
//        tree.postOrder(tree.root);

        tree.convertToDL(tree.root);
    }
}
