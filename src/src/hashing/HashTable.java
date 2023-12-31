package hashing;

import student.Student;

import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private List<Student>[] table;
    private int modulo = 199;

    public HashTable() {
        table = new LinkedList[modulo];
        for (int i = 0; i < modulo; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public List<Student>[] getTable() {
        return table;
    }

    public int getModulo() {
        return modulo;
    }

    public void insert(int studentID, Student student) {
        int hashValue = hashStudentID(studentID);

        List<Student> chain = table[hashValue];
        boolean studentExists = false;

        for (Student existedStudent : chain) {
            if (existedStudent.getStudentID() == studentID) {
                System.out.println("Sinh viên " + studentID + " đã tồn tại!");
                studentExists = true;
                break;
            }
        }

        if (!studentExists) {
            chain.add(student);
        }
    }

    public int hashStudentID(int studentID) {
        int firstPart = studentID / 10000;
        int secondPart = studentID % 10000;
        int sum = firstPart + secondPart;
        return sum % modulo;
    }

    public Student get(int studentID) {
        int hashValue = hashStudentID(studentID);

        List<Student> chain = table[hashValue];
        for (Student student : chain) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }

        return null;
    }

    public void updateScore(int studentID, float newScore) {
        int hashValue = hashStudentID(studentID);

        List<Student> chain = table[hashValue];
        for (Student student : chain) {
            if (student.getStudentID() == studentID) {
                student.setScore(newScore);
                System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
                System.out.println("********************************");
                return;
            }
        }

        System.out.println("Không có sinh viên với MSSV " + studentID + " trong danh sách");
        System.out.println("********************************");
    }

    public void delete(int studentID) {
        int hashValue = hashStudentID(studentID);

        List<Student> chain = table[hashValue];
        for (Student student : chain) {
            if (student.getStudentID() == studentID) {
                chain.remove(student);
                System.out.println("Đã xóa sinh viên " + studentID + " ra khỏi danh sách");
                System.out.println("********************************");
                return;
            }
        }

        System.out.println("Không có sinh viên với MSSV " + studentID + " trong danh sách");
        System.out.println("********************************");
    }

    public void printHashTable() {
        System.out.println("Danh sách sinh viên trong hashtable:");
        for (int i = 0; i < table.length; i++) {
            if (table[i].isEmpty()) {
            }
            else {
                System.out.print("At " + i + ": ");
                for (Student student : table[i]) {
                    System.out.print(student.getStudentID() + " - ");
                }
                System.out.println();
            }
        }
    }
}
