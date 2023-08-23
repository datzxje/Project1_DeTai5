package hashing;

import student.Student;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HashTable {
    private Hashtable<Integer, Student> table;

    public HashTable() {
        table = new Hashtable<>();
    }

    public void insert(int studentID, String name, int yearOfBirth, float score, float avgScore) {
        Student student = new Student(name, yearOfBirth, score, avgScore);
        table.put(studentID, student);
    }

    public Student get(int studentID) {
        return table.get(studentID);
    }

    public void updateScore(int studentID, float newScore) {
        Student student = (Student) table.get(studentID);
        if (student != null) {
            student.setScore(newScore);
            System.out.println("Điểm của sinh viên " + studentID + " đã được cập nhật");
            System.out.println("********************************");
        }
    }

    public void delete(int studentID) {
        if (table.containsKey(studentID)) {
            table.remove(studentID);
            System.out.println("Đã xóa sinh viên ra khỏi danh sách với MSSV " + studentID);
            System.out.println("********************************");
        }
        else {
            System.out.println("Không có sinh viên với MSSV " + studentID + " trong danh sách");
            System.out.println("********************************");
        }
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        allStudents.addAll(table.values());
        return allStudents;
    }

    public List<Integer> getAllStudentIDs() {
        return new ArrayList<>(table.keySet());
    }
}
