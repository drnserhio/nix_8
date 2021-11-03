package com.ua.alevel.gamesecond.treedepth;

public final class Tree {

    private static TreeNode root;
    private static int countLeft;
    private static int coutRigth;

    private Tree() {}

    public static void setRoot(int rootValue) {
        Tree.root = new TreeNode(rootValue);
    }

    public static void putChild(int value) {

        if (root == null) {
            root = new TreeNode(value);
            return;
        }

        TreeNode node = root;
        while (true) {
            if (value < node.value) {
                if (node.leftChild == null) {
                    node.leftChild = new TreeNode(value);
                    countLeft++;
                    return;
                } else {
                    node = node.leftChild;
                }
            } else if (value > node.value) {
                if (node.rightChild == null) {
                    node.rightChild = new TreeNode(value);
                    coutRigth++;
                    return;
                } else {
                    node = node.rightChild;
                }
            } else {
                return;
            }
        }
    }

    public static int countTreeDepth() {
        return Math.max(coutRigth, countLeft);
    }

}

