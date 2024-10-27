import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades); // создаем изменяемый список
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public void incrementCourse() {
        this.course++;
    }

    @Override
    public String toString() {
        return name + " (Группа: " + group + ", Курс: " + course + ", Средний балл: " + getAverageGrade() + ", Оценки: " + grades + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем коллекцию студентов
        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван Иванов", "Группа A", 2, List.of(4, 5, 3)));
        students.add(new Student("Петр Петров", "Группа B", 1, List.of(2, 3, 2)));
        students.add(new Student("Мария Смирнова", "Группа A", 3, List.of(5, 4, 4)));
        students.add(new Student("Анна Кузнецова", "Группа C", 2, List.of(3, 3, 3)));


        System.out.println("До перевода:");
        printAllStudents(students);

        // Удаляем студентов со средним баллом < 3
        filterStudentsWithLowGrades(students);

        // Переводим оставшихся студентов на следующий курс
        promoteStudents(students);


        System.out.println("\nПосле перевода:");
        printAllStudents(students);
    }

    // Метод для фильтрации студентов с баллом < 3
    public static void filterStudentsWithLowGrades(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                iterator.remove();
            }
        }
    }

    // Метод для перевода студентов на следующий курс
    public static void promoteStudents(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() >= 3) {
                student.incrementCourse();
            } else {
                iterator.remove();
            }
        }
    }


    public static void printAllStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Нет студентов в списке.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
