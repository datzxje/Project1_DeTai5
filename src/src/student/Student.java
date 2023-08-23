package student;

public class Student {
    protected String name;
    protected int yearOfBirth;
    protected float score;
    protected float avgScore;

    public Student(String name, int yearOfBirth, float score, float avgScore) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.score = score;
        this.avgScore = avgScore;
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
}
