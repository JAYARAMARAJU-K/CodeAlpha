import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeCalculator 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        System.out.println("Enter student grades (type 0 to finish):");
        while (true) 
        {
            int grade = scanner.nextInt();
            if (grade == 0) 
            {
                break;
            }
            grades.add(grade);
        }
        double average = calculateAverage(grades);
        int highest = Collections.max(grades);
        int lowest = Collections.min(grades);
        System.out.println("Average grade: " + average);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
        scanner.close();
    }
    private static double calculateAverage(ArrayList<Integer> grades) 
    {
        int sum = 0;
        for (int grade : grades) 
        {
            sum += grade;
        }
        return sum / (double) grades.size();
    }
}
