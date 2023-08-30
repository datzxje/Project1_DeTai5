package avl_tree;

import student.Student;


public class AVLTree {
    protected Node root;

    public AVLTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private int height(Node node) {
        return node != null ? node.height : -1;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;

        node.height = Math.max(height(node.left),height(node.right)) + 1;
        temp.height = Math.max(height(temp.left),height(temp.right)) + 1;

        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;

        node.height = Math.max(height(node.left),height(node.right)) + 1;
        temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
        return temp;
    }

    private Node leftRightRotate(Node node) {
        node.left = rotateRight(node.left);
        return rotateLeft(node);
    }

    private Node rightLeftRotate(Node node) {
        node.right = rotateLeft(node.right);
        return rotateRight(node);
    }

    public Node insert(Node node, int studentID, Student student) {
        if (node == null) {
            node = new Node(studentID, student);
        }

        else if (studentID < node.getStudentID()) {
            node.left = insert(node.left, studentID, student);
            if (height(node.left) - height(node.right) == 2) {
                if (studentID < node.left.getStudentID())
                    node = rotateLeft(node);
                else node = leftRightRotate(node);
            }
        }
        else if (studentID > node.getStudentID()) {
            node.right = insert(node.right, studentID, student);
            if (height(node.right) - height(node.left) == 2) {
                if (studentID > node.right.getStudentID())
                    node = rotateRight(node);
                else node = rightLeftRotate(node);
            }
        }
        else;
        node.height = Math.max(height(node.left), height(node.right));

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

    public Node delete(Node node, int studentID) {
        if (node == null) {
            return null; // Node not found, return null
        }

        if (studentID < node.getStudentID()) {
            node.left = delete(node.left, studentID);
        } else if (studentID > node.getStudentID()) {
            node.right = delete(node.right, studentID);
        } else {

            if (node.left == null || node.right == null) {
                // Node has at most one child
                if (node.left != null) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            } else {
                // Node has two children
                Node minRight = findMin(node.right);
                node.setStudentID(minRight.getStudentID());
                node.setStudent(minRight.getStudent());
                node.right = delete(node.right, minRight.getStudentID());
            }
        }

        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            int balance = height(node.left) - height(node.right);

            if (balance > 1) {
                if (height(node.left.left) >= height(node.left.right)) {
                    node = rotateRight(node);
                } else {
                    node = leftRightRotate(node);
                }
            } else if (balance < -1) {
                if (height(node.right.right) >= height(node.right.left)) {
                    node = rotateLeft(node);
                } else {
                    node = rightLeftRotate(node);
                }
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
    
}
