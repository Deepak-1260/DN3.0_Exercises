class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grade: " + grade);
    }
}

class StudentController {
    private Student student;
    private StudentView view;

    public StudentController(Student student, StudentView view) {
        this.student = student;
        this.view = view;
    }

    public void setStudentName(String name) {
        student.setName(name);
    }

    public String getStudentName() {
        return student.getName();
    }

    public void setStudentId(String id) {
        student.setId(id);
    }

    public String getStudentId() {
        return student.getId();
    }

    public void setStudentGrade(String grade) {
        student.setGrade(grade);
    }

    public String getStudentGrade() {
        return student.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(student.getName(), student.getId(), student.getGrade());
    }
}

public class MVCPattern {
    public static void main(String[] args) {
        Student student = new Student("1", "John Doe", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.updateView();
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");
        controller.updateView();
    }
}
