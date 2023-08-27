package avl_tree;

import student.Student;

public class Node {
    protected int studentID;
    protected Student student;
    protected int height;
    protected Node left;
    protected Node right;

    public Node(int studentID, Student student) {
        this.studentID = studentID;
        this.student = student;
        this.height = 1;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
