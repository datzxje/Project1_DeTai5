package avl_tree;

import student.Student;

public class AVLTest {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        Student linh = new Student(4, "Linh",2003,30,9);
        Student dat = new Student(2,"Bui Duc",2002,28.6f,8.9f);
        Student x = new Student(3,"Nguyen Hoan",2003,25.9f,9.1f);
        Student y = new Student(1,"a",25,2,4);
        Student z = new Student(6,"aa",23,4,5);
        Student q = new Student(8,"a",4,5,6);
        avlTree.insert(4, linh);
        avlTree.insert(2, dat);
        avlTree.insert(3,x);
        avlTree.insert(1,y);
        avlTree.insert(6,z);
        avlTree.insert(8,q);
        avlTree.delete(avlTree.getRoot(),6);

        avlTree.getRoot().setHeight(Math.max(avlTree.height(avlTree.getRoot().left),avlTree.height(avlTree.getRoot().right)) + 1);
        System.out.println(avlTree.getRoot().getHeight());
        print2DUtil(avlTree.getRoot(),0);
    }

    private static final int COUNT = 4; // Adjust the spacing as needed

    public static void print2DUtil(Node root, int space) {
        if (root == null)
            return;
        space += COUNT;

        print2DUtil(root.right, space);
        System.out.println();

        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(root.getStudentID() + " (" + root.height + ") ");
        System.out.println();

        print2DUtil(root.left, space);
    }
}
