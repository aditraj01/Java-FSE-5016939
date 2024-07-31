public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        Employee emp1 = new Employee(1, "Alice", "Manager", 75000);
        Employee emp2 = new Employee(2, "Bob", "Developer", 60000);
        Employee emp3 = new Employee(3, "Charlie", "Designer", 55000);

        ems.addEmployee(emp1);
        ems.addEmployee(emp2);
        ems.addEmployee(emp3);

        System.out.println("All Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for Employee with ID 2:");
        Employee foundEmployee = ems.searchEmployee(2);
        if (foundEmployee != null) {
            System.out.println(foundEmployee);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting Employee with ID 2:");
        ems.deleteEmployee(2);

        System.out.println("All Employees after deletion:");
        ems.traverseEmployees();
    }
}