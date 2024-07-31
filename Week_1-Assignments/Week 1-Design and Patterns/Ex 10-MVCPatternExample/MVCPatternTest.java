public class MVCPatternTest {

	public static void main(String[] args) {
        Student student = new Student("5016939", "Aditya Raj", "A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();
        controller.setStudentGrade("B");
        System.out.println();
        controller.updateView();
	}

}
