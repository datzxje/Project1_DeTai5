package hashing;

import student.Student;

import java.io.*;
import java.util.Scanner;

public class HashProgram {

    private static Scanner keyboard = new Scanner(System.in);
    private static String path = "src/student_data/student_data.csv";

    public static void main(String[] args) {

        iniMenu();
        int opt = keyboard.nextInt();

        while (opt != 0) {
            HashTable hashTable = new HashTable();
            switch (opt) {
                case 1:
                    insertStudent();
                    break;
                case 2:
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
                                hashTable.insert(studentID, student);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    program(hashTable);
                    break;
            }
            iniMenu();
            opt = keyboard.nextInt();
        }
    }

    private static void insertStudent() {
        System.out.println("Hãy nhập thông tin sinh viên: ");
        System.out.print("MSSV (VD: 20215341): ");
        int studentID = keyboard.nextInt();

        if (!studentExists(studentID)) {
            keyboard.nextLine(); // Đọc dòng trống sau nextInt
            System.out.print("Họ và tên: ");
            String name = keyboard.nextLine();
            System.out.print("Năm sinh: ");
            int yearOfBirth = keyboard.nextInt();
            System.out.print("Điểm thi: ");
            float score = keyboard.nextFloat();
            System.out.print("Điểm trung bình: ");
            float avgScore = keyboard.nextFloat();

            try {
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.println(studentID + "," + name + "," + yearOfBirth + "," + score + "," + avgScore);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sinh viên với MSSV  " + studentID + " đã tồn tại trong file.");
        }
    }

    private static boolean studentExists(int studentID) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    int existingStudentID = Integer.parseInt(parts[0]);
                    if (existingStudentID == studentID) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void showStudentInf(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();
        Student student = hashTable.get(studentID);

        if (student != null) {
            System.out.println("--------------------------------");
            System.out.println("Student ID: " + studentID);
            System.out.println("Name: " + student.getName());
            System.out.println("Year Of Birth: " + student.getYearOfBirth());
            System.out.println("Score: " + student.getScore());
            System.out.println("Average Score: " + student.getAvgScore());
            System.out.println("********************************");
        }
        else System.out.println("Không có sinh viên " + studentID + " trong danh sách");
    }

    private static void updateScore(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        System.out.println("Nhập vào điểm mới: ");
        float score = keyboard.nextFloat();

        hashTable.updateScore(studentID, score);
        updateScoreInputFile(studentID,hashTable,score);
    }

    private static void deleteStudent(HashTable hashTable) {
        System.out.println("Nhập vào MSSV: ");
        int studentID = keyboard.nextInt();

        hashTable.delete(studentID);
        System.out.println("Đã xóa sinh viên " + studentID + " ra khỏi danh sách");
        deleteStudentInputFile(studentID);
    }

    private static void updateScoreInputFile(int studentID, HashTable hashTable, float newScore) {

        Student wantedStudent = hashTable.get(studentID);
        try {
            File inputFile = new File(path);
            File tempFile = new File("src/student_data/temp.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean updated = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(String.valueOf(studentID))) {
                    // Cập nhật dòng
                    line = wantedStudent.getStudentID() + "," + wantedStudent.getName() + "," + wantedStudent.getYearOfBirth() + "," + wantedStudent.getScore() + "," + wantedStudent.getAvgScore();
                    updated = true;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();

            if (updated) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
            }
            else{
                tempFile.delete(); // Không cập nhật, xóa file tạm
                System.out.println("Không tìm thấy dòng cần cập nhật.");
            }
        }
        catch(IOException ignored){
        }
    }

    private static void deleteStudentInputFile(int studentID) {

        try {
            File inputFile = new File(path);
            File tempFile = new File("src/student_data/temp.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean updated = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(String.valueOf(studentID))) {
                    updated = true;
                   continue;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();

            if (updated) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
            }
            else{
                tempFile.delete();
            }
        }
        catch(IOException ignored){
        }
    }

    public static void program(HashTable hashTable) {
        showMenu();

        int option = keyboard.nextInt();

        while (option != 0) {
            switch (option) {
                case 1 -> showStudentInf(hashTable);
                case 2 -> updateScore(hashTable);
                case 3 -> deleteStudent(hashTable);
            }

            showMenu();
            option = keyboard.nextInt();
        }
    }

    public static void iniMenu() {
        System.out.println("Student Management Application: ");
        System.out.println("--------------------------------");
        System.out.println("1. Insert new student");
        System.out.println("2. Program");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }
    public static void showMenu () {
        System.out.println("Student Management Application: ");
        System.out.println("--------------------------------");
        System.out.println("1. Show student information by id");
        System.out.println("2. Update score by id");
        System.out.println("3. Delete student by id");
        System.out.println("0. Back to menu");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }
}
