package com.zcj.demo.testcollection.binarytreen;

/**
 * @Auther: 10062376
 * @Date: 2019/5/14 11:07
 * @Description:
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * 题目描述：输入一个二叉树，输出其镜像(反转二叉树)。
 * */
public class ReverseBinaryTreeTest {

    public static void main(String[] args) {
        ReverseBinaryTreeTest reverse = new ReverseBinaryTreeTest();
        TreeNode root = null;
        root = reverse.createTree(root);
        System.out.println("原二叉树的层次遍历");
        reverse.levelTraverse(root);
        reverse.Mirror(root);
        System.out.println("\n输出该二叉树的镜像:递归方式");
        reverse.levelTraverse(root);
        reverse.invertTree2(root);
        System.out.println("\n输出该二叉树的镜像:非递归方式借助栈");
        reverse.levelTraverse(root);
        reverse.invertTree3(root);
        System.out.println("\n输出该二叉树的镜像:非递归方式借助队列");
        reverse.levelTraverse(root);
    }

    Scanner scanner = new Scanner(System.in);

    // 建立二叉树
    public TreeNode createTree(TreeNode root) {
        String val;
        val = scanner.next(); // next方法每次取到一个间隔符前面的数据
        if (val.equals("#")) {
            return null;
        }
        root = new TreeNode(Integer.parseInt(val));
        System.out.println("输入的数据为：" + val);
        root.left = createTree(root.left);
        root.right = createTree(root.right);
        return root;
    }

    // 层次遍历二叉树
    public void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        while (list.size() != 0) {
            TreeNode node = list.removeFirst(); // list.removeFirst() 该方法LinkedList才有
            System.out.print(node.val + " ");
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        }
    }


    // 得到二叉树的镜像  —— 递归的方式
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if ((root.left == null) && (root.right == null)) {
            return;
        }
        //递归反转左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);

        // root.left = invertTree(root.right);
        // root.right = invertTree(temp);
    }

    //得到二叉树的镜像：非递归层次遍历，借助栈
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);//先将根节点压入堆栈
        while (stack.size() > 0) {
            //根据栈的先进后出操作，获取栈中最后一个元素，即最先入栈的元素
            TreeNode temp = stack.lastElement();
            stack.pop();//弹出栈顶元素

            //交换左右子树
            TreeNode tempLeft = temp.left;
            temp.left = temp.right;
            temp.right = tempLeft;

            //左子树不为空，将左子树入栈
            if (temp.left != null) {
                stack.push(temp.left);
            }
            //右子树不为空，将右子树入栈
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }

        return root;
    }

    //得到二叉树的镜像：非递归层次遍历，借助于队列，操作与栈类似，只不过元素是先进先出。
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }

        //LinkedList实现了集合框架的Queue接口
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//加入元素
        while (queue.size() > 0) {
            TreeNode temp = queue.poll();//获取并移出元素

            TreeNode tempLeft = temp.left;
            temp.left = temp.right;
            temp.right = tempLeft;

            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }

        return root;
    }
}

/*
 * 测试数据：
 * 1 2 3 # 4 # # 5 6 # # # 7 8 # # 9 10 # # 11 # #  （说明：其中#说明左右子树为空）
 * 用先序遍历来建立树后，层次遍历结果为： 1 2 7 3 5 8 9 4 6 10 11
 * 反转二叉树之后：1 7 2 9 8 5 3 11 10 6 4
 */
