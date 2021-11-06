package com.ua.alevel.gamesecond.treedepth;

public final class Tree {

    private TreeNode root;


    public Tree() {
        this.root = null;
    }


    public void putChild(int value) {

        if (root == null) {
            root = new TreeNode(value);
            return;
        }

        TreeNode node = root;
        while (true) {
            if (value < node.value) {
                if (node.leftChild == null) {
                    node.leftChild = new TreeNode(value);
                    return;
                } else {
                    node = node.leftChild;
                }
            } else if (value > node.value) {
                if (node.rightChild == null) {
                    node.rightChild = new TreeNode(value);
                    return;
                } else {
                    node = node.rightChild;
                }
            } else {
                return;
            }
        }
    }

    public static int countTreeDepth(TreeNode node) {
        if (node == null) return 0;
        int L = countTreeDepth(node.leftChild);
        int R = countTreeDepth(node.rightChild);
        return Math.max(L, R) + 1 ;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}

