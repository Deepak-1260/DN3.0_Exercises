public class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: $" + salary;
    }
}

public class EmployeeManager {
    private Employee[] employeeArray;
    private int currentSize;

    public EmployeeManager(int capacity) {
        employeeArray = new Employee[capacity];
        currentSize = 0;
    }

    public void addEmployee(Employee employee) {
        if (currentSize < employeeArray.length) {
            employeeArray[currentSize++] = employee;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    public Employee findEmployeeById(int employeeId) {
        for (int i = 0; i < currentSize; i++) {
            if (employeeArray[i].getEmployeeId() == employeeId) {
                return employeeArray[i];
            }
        }
        return null;
    }

    public void printAllEmployees() {
        for (int i = 0; i < currentSize; i++) {
            System.out.println(employeeArray[i]);
        }
    }

    public boolean removeEmployeeById(int employeeId) {
        for (int i = 0; i < currentSize; i++) {
            if (employeeArray[i].getEmployeeId() == employeeId) {
                for (int j = i; j < currentSize - 1; j++) {
                    employeeArray[j] = employeeArray[j + 1];
                }
                employeeArray[--currentSize] = null;
                return true;
            }
        }
        return false;
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(10);

        manager.addEmployee(new Employee(1, "Alice", "Developer", 70000));
        manager.addEmployee(new Employee(2, "Bob", "Manager", 85000));
        manager.addEmployee(new Employee(3, "Charlie", "Analyst", 60000));
        manager.addEmployee(new Employee(4, "David", "Designer", 65000));

        System.out.println("All employees:");
        manager.printAllEmployees();

        System.out.println("\nSearching for employee with ID 3:");
        Employee employee = manager.findEmployeeById(3);
        if (employee != null) {
            System.out.println("Found: " + employee);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting employee with ID 2:");
        boolean isDeleted = manager.removeEmployeeById(2);
        System.out.println("Deleted: " + isDeleted);

        System.out.println("\nAll employees after deletion:");
        manager.printAllEmployees();
    }
}
