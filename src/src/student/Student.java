package student;

public class Student {
    protected int studentID;
    protected String name;
    protected int yearOfBirth;
    protected float score;
    protected float avgScore;

    public Student(int studentID, String name, int yearOfBirth, float score, float avgScore) {
        this.studentID = studentID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.score = score;
        this.avgScore = avgScore;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return (
                "--------------------------------\n" +
                        "Student ID: " + studentID + "\n" +
                        "Name: " + name + "\n" +
                        "Year Of Birth: " + yearOfBirth + "\n" +
                        "Score: " + score + "\n" +
                        "Average Score: " + avgScore + "\n" +
                        "******************************"
        );
    }
}
