package com.ua.alevel.gamesecond.treedepth;

import lombok.ToString;


@ToString
public class TreeNode {

    int value;
    TreeNode leftChild;
    TreeNode rightChild;


    public TreeNode(int value) {
        this.value = value;
    }

}
