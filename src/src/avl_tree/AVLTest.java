package avl_tree;

import student.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AVLTest {
    private static String path = "src/student_data/student_data.txt";
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int studentID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int yearOfBirth = Integer.parseInt(parts[2]);
                    float score = Float.parseFloat(parts[3]);
                    float avgScore = Float.parseFloat(parts[4]);
                    Student student = new Student(studentID, name, yearOfBirth, score, avgScore);
                    avlTree.insert(studentID, student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Cây ban đầu: \n");
        print2DUtil(avlTree.getRoot(), 0);
        System.out.println("********************************");
        System.out.println("Cây sau khi xóa node 20216568: \n");
        avlTree.delete(avlTree.getRoot(),20216568);
        print2DUtil(avlTree.getRoot(),0);
        System.out.println("********************************");

    }

    private static final int COUNT = 4;

    public static void print2DUtil(Node root, int space) {
        if (root == null)
            return;
        space += COUNT;

        print2DUtil(root.right, space);
        System.out.println();

        for (int i = COUNT; i < space; i++) {
            System.out.print("     ");
        }
        System.out.print(root.getStudentID() + " (" + root.height + ") ");
        System.out.println();

        print2DUtil(root.left, space);
    }
}
