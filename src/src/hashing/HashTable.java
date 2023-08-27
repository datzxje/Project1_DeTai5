package hashing;

import student.Student;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.lang.Math.pow;

public class HashTable {
    private Student[] table;
    private int modulo = 3307;

    public HashTable() {
        table = new Student[modulo];
    }

    public void insert(int studentID, Student student) {
        int hashValued = hashStudentID(studentID);

        int index = hashValued;
        while (table[index] != null) {
            index = (index + 1) % modulo;
        }

        table[index] = student;
    }

    public int hashStudentID(int studentID) {
        int firstPart = studentID / 10000; // Lấy 4 chữ số đầu tiên
        int secondPart = studentID % 10000; // Lấy 4 chữ số cuối cùng
        int sum = firstPart + secondPart; // Tính tổng hai phần
        return sum % modulo; // Băm tổng theo phương thức chia lấy dư với số nguyên tố
    }

    public Student get(int studentID) {
        int hashValue = hashStudentID(studentID);

        int index = hashValue;
        while (table[index] != null) {
            if (table[index].getStudentID() == studentID) {
                return table[index];
            }
            index = (index + 1) % modulo;
        }

        return null; // Không tìm thấy
    }

    public void updateScore(int studentID, float newScore) {
        Student student = get(studentID);
        if (student != null) {
            student.setScore(newScore);
            System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
            System.out.println("********************************");
        }
    }

    public void delete(int studentID) {
        int hashValue = hashStudentID(studentID);

        int index = hashValue;
        while (table[index] != null) {
            if (table[index].getStudentID() == studentID) {
                table[index] = null;
                System.out.println("Đã xóa sinh viên ra khỏi danh sách với MSSV " + studentID);
                System.out.println("********************************");
                return;
            }
            index = (index + 1) % modulo;
        }

        System.out.println("Không có sinh viên với MSSV " + studentID + " trong danh sách");
        System.out.println("********************************");
    }

    public List<Integer> getAllStudentIDs() {
        List<Integer> studentIDs = new ArrayList<>();
        for (Student student : table) {
            if (student != null) {
                studentIDs.add(student.getStudentID());
            }
        }
        return studentIDs;
    }
}
