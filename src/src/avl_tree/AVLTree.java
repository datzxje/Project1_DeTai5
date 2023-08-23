package avl_tree;

import student.Student;


public class AVLTree {
    private Node root;

    public AVLTree() {
        root = null;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateLeft(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateRight(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    public Node insert(Node node, int studentID, Student student) {
        if (node == null) {
            return new Node(studentID,student);
        }

        if (studentID < node.studentID) {
            node.left = insert(node.left, studentID, student);
        }
        else if (studentID > node.studentID) {
            node.right = insert(node.right, studentID, student);
        }
        else return null;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && studentID < node.left.studentID) {
            return rotateRight(node);
        }

        // Right Right Case
        if (balance < -1 && studentID > node.right.studentID) {
            return rotateLeft(node);
        }

        // Left Right Case
        if (balance > 1 && studentID > node.left.studentID) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (balance < -1 && studentID < node.right.studentID) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
    public void insert(int studentID, Student student) {
        root = insert(root, studentID, student);
    }

    public void showStudentInf(int studentID) {
        Node node = search(root, studentID);
        if (node != null) {
            System.out.println("Student ID: " + studentID);
            System.out.println("Name: " + node.student.getName());
            System.out.println("Year Of Birth: " + node.student.getYearOfBirth());
            System.out.println("Score: " + node.student.getScore());
            System.out.println("Average Score: " + node.student.getAvgScore());
            System.out.println("********************************");
        } else {
            System.out.println("Không có sinh viên " + studentID + " trong danh sách");
        }
    }

    public void updateScore(int studentID, float newScore) {
        Node node = search(root, studentID);
        if (node != null) {
            node.student.setScore(newScore);
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    public void deleteStudent(int studentID) {
        root = delete(root, studentID);
    }

    private Node search(Node node, int studentID) {
        if (node == null || node.studentID == studentID) {
            return node;
        }

        if (studentID < node.studentID) {
            return search(node.left, studentID);
        } else {
            return search(node.right, studentID);
        }
    }

    private Node delete(Node node, int studentID) {
        if (root == null) return root;
        if (root.studentID < studentID) root.left = delete(root.left, studentID);
        else if (root.studentID > studentID) root.right = delete(root.right, studentID);
        else {
            if (root.left == null || root.right == null) {
                Node temp = null;
                if (temp == root.left) temp = root.right;
                else temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                }
                else root = temp;
            }
            else {
                Node temp = minValueNode(root.right);
                root.studentID = temp.studentID;
                root.right = delete(root.right, temp.studentID);
            }
        }

        if (root == null) return root;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

}
