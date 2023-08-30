package generate_data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateStudentData {
    public static void main(String[] args) {
        int numStudents = 5000;
        String filename = "src/student_data/student_data.csv";

        String[] firstNames = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Huynh", "Vu", "Dang", "Bui", "Do"};
        String[] lastNames = {"Van", "Thi", "Minh", "Duc", "Huong", "Hai", "Nhu", "Hoan", "Thanh", "Tuan"};

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Ma sinh vien,Ho ten,Nam sinh,Diem trung tuyen,Diem TB\n");

            for (int i = 0; i < numStudents; i++) {
                String studentId = generateStudentId();
                String name = generateName(firstNames, lastNames);
                String birthYear = generateBirthYear();
                String admissionScore = generateScore();
                String avgScore = generateAvgScore();

                String line = String.join(",", studentId, name, birthYear, admissionScore, avgScore);
                writer.write(line + "\n");
            }

            System.out.println("Generated data for " + numStudents + " students and saved to '" + filename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateStudentId(){
            int numberOfRandomNumbers = 5000;
            Random random = new Random();
            String formattedNumber = "";

            for (int i = 0; i < numberOfRandomNumbers; i++) {
                int randomNumber = random.nextInt(100000000);
                formattedNumber = String.format("%08d", randomNumber);
            }
            return formattedNumber;
    }

    private static String generateName(String[] firstNames, String[] lastNames) {
        String firstName = firstNames[new Random().nextInt(firstNames.length)];
        String lastName = lastNames[new Random().nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    private static String generateBirthYear() {
        return String.valueOf(2002 + new Random().nextInt(2));
    }

    private static String generateScore() {
        return String.format("%.1f", 25.0 + new Random().nextDouble() * 5.0);
    }

    private static String generateAvgScore() {
        return String.format("%.1f", 7.5 + new Random().nextDouble() * 2.5);
    }
}
