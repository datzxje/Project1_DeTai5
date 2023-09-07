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

    public int height(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        Node temp2 = temp.left;
        temp.left = node;
        node.right = temp2;

        node.height = Math.max(height(node.left),height(node.right)) + 1;
        temp.height = Math.max(height(temp.left),height(temp.right)) + 1;

        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        Node temp2 = temp.right;
        temp.right = node;
        node.left = temp2;

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
        if (node == null)
            return (new Node(studentID,student));

        if (studentID < node.getStudentID())
            node.left = insert(node.left, studentID, student);
        else if (studentID > node.getStudentID())
            node.right = insert(node.right, studentID,student);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        //Left Left Case
        if (balance > 1 && studentID < node.left.getStudentID())
            return rotateRight(node);

        // Right Right Case
        if (balance < -1 && studentID > node.right.getStudentID())
            return rotateLeft(node);

        // Left Right Case
        if (balance > 1 && studentID > node.left.getStudentID()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (balance < -1 && studentID < node.right.getStudentID()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public void insert(int studentID, Student student) {
        root = insert(root, studentID, student);
    }

    public String showStudentInf(int studentID) {
        Node node = search(root, studentID);
        if (node != null) {
            String studentInfo = "Student ID: " + studentID + "\n" +
                    "Name: " + node.student.getName() + "\n" +
                    "Year Of Birth: " + node.student.getYearOfBirth() + "\n" +
                    "Score: " + node.student.getScore() + "\n" +
                    "Average Score: " + node.student.getAvgScore();
            System.out.println(studentInfo);  // In ra màn hình console (không bắt buộc)
            return studentInfo;
        } else {
            System.out.println("Không có sinh viên " + studentID + " trong danh sách");
            return null;  // Trả về null nếu không tìm thấy sinh viên
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
            System.out.println("Không có sinh viên " + studentID + " trong danh sách");
            return null;
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

            int balance = getBalance(node);

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
