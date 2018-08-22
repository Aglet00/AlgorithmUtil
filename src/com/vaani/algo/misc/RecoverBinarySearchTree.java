package com.vaani.algo.misc;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.ArrayList;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
/*
Straight forward approach:
Inorder traverse the tree, put the nodes in a treenode list and the values in an integer list.
Sort the value list and update the treenode list's values one by one.
*/
/*
Inorder traverse, keep the previous tree node,
Find first misplaced node by
if ( current.val < prev.val )
   ListNode first = prev;

Find second by
if ( current.val < prev.val )
   ListNode second = current;

After traversal, swap the values of first and second node. Only need two pointers, prev and current node. O(1) space.
*/
public class RecoverBinarySearchTree {
    ArrayList<TreeNode<Integer>> track;
    TreeNode<Integer> previous;

    public void recoverTree(TreeNode<Integer> root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        track = new ArrayList<TreeNode<Integer>>();
        previous = null;
        inorderTraverse(root);

        int size = track.size();
        int temp = track.get(0).val;
        track.get(0).val = track.get(size - 1).val;
        track.get(size - 1).val = temp;
    }

    public void inorderTraverse(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left);
        if (previous != null && previous.val > root.val) {
            if (!track.contains(previous)) {
                track.add(previous);
            }
            if (!track.contains(root)) {
                track.add(root);
            }
        }
        previous = root;
        inorderTraverse(root.right);
    }
}
