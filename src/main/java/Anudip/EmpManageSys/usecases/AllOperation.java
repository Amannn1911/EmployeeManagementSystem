package Anudip.EmpManageSys.usecases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Anudip.EmpManageSys.exception.EmployeeException;
import Anudip.EmpManageSys.model.Employee;
import Anudip.EmpManageSys.service.EmployeeService;
import Anudip.EmpManageSys.serviceImpl.EmployeeServiceImpl;

public class AllOperation {

    public static Scanner sc = new Scanner(System.in);
    public static EmployeeService es = new EmployeeServiceImpl();

    // Method to input employee details
    public static Employee inputFromEmployee() {
        System.out.print("Enter Employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee username: ");
        String username = sc.nextLine();

        System.out.print("Enter Employee password: ");
        String password = sc.nextLine();

        System.out.print("Enter Employee status (e.g., active, inactive): ");
        String status = sc.nextLine();

        System.out.print("Enter Employee role (e.g., admin, user , developer , tester): ");
        String role = sc.nextLine();

        Employee employee = new Employee();
        employee.setEmployeeName(name);
        employee.setEmployeeUserName(username);
        employee.setPassword(password);
        employee.setStatus(status);
        employee.setRole(role);

        return employee;
    }

    // Main method for employee operations
    public static void employeeOperation() throws EmployeeException {

        while (true) {
            System.out.println("\nWelcome to Employee Management System\n"
                               + "1. Create Employee Account\n"
                               + "2. Delete Employee Account\n"
                               + "3. Update Employee Account\n"
                               + "4. Find Employee Account\n"
                               + "5. View All Employees\n"
                               + "6. Validate Employee Credentials\n"
                               + "7. Update Employee Role\n"
                               + "8. Update Employee Status\n"
                               + "9. Update Employee Password\n"
                               + "10. Find Employees by Name\n"
                               + "11. Find Employees by Status\n"
                               + "12. Get Employees by Role\n"
                               + "13. Count Employees\n"
                               + "14. Exit\n");

            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 14.");
                sc.nextLine(); // clear scanner buffer
                continue;
            }

            sc.nextLine(); // clear buffer after reading integer

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Creating a new Employee account...");
                        Employee newEmp = inputFromEmployee();
                        Employee createdEmp = es.createEmpAccount(newEmp);

                        if (createdEmp != null) {
                            System.out.println("Employee created successfully: " + createdEmp);
                        } else {
                            System.out.println("Failed to create employee. Please try again.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId = sc.nextInt();
                        boolean isDeleted = es.deleteEmp(deleteId);

                        if (isDeleted) {
                            System.out.println("Employee deleted successfully.");
                        } else {
                            System.out.println("Failed to delete employee or employee not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Updating Employee account...");
                        Employee updateEmp = inputFromEmployee();

                        System.out.print("Enter Employee ID to update: ");
                        int updateId = sc.nextInt();
                        updateEmp.setEmployeeId(updateId);

                        boolean isUpdated = es.updateEmp(updateEmp);

                        if (isUpdated) {
                            System.out.println("Employee updated successfully.");
                        } else {
                            System.out.println("Failed to update employee or employee not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Employee ID to find: ");
                        int findId = sc.nextInt();
                        Employee foundEmp = es.findEmp(findId);

                        if (foundEmp != null) {
                            System.out.println("Employee found: " + foundEmp);
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Viewing all employees...");
                        List<Employee> allEmployees = es.getAllEmployees();
                        if (allEmployees != null && !allEmployees.isEmpty()) {
                            allEmployees.forEach(employee -> System.out.println(employee));
                        } else {
                            System.out.println("No employees found.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter Employee username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter Employee password: ");
                        String password = sc.nextLine();
                        boolean isValid = es.validateEmployeeCredentials(username, password);
                        System.out.println(isValid ? "Credentials are valid." : "Invalid credentials.");
                        break;

                    case 7:
                        System.out.print("Enter Employee ID to update role: ");
                        int roleId = sc.nextInt();
                        sc.nextLine();  // clear buffer
                        System.out.print("Enter new role: ");
                        String newRole = sc.nextLine();
                        boolean roleUpdated = es.updateEmployeeRole(roleId, newRole);
                        System.out.println(roleUpdated ? "Role updated successfully." : "Failed to update role.");
                        break;

                    case 8:
                        System.out.print("Enter Employee ID to update status: ");
                        int statusId = sc.nextInt();
                        sc.nextLine();  // clear buffer
                        System.out.print("Enter new status: ");
                        String newStatus = sc.nextLine();
                        boolean statusUpdated = es.updateEmployeeStatus(statusId, newStatus);
                        System.out.println(statusUpdated ? "Status updated successfully." : "Failed to update status.");
                        break;

                    case 9:
                        System.out.print("Enter Employee ID to update password: ");
                        int passwordId = sc.nextInt();
                        sc.nextLine();  // clear buffer
                        System.out.print("Enter new password: ");
                        String newPassword = sc.nextLine();
                        boolean passwordUpdated = es.updateEmployeePassword(passwordId, newPassword);
                        System.out.println(passwordUpdated ? "Password updated successfully." : "Failed to update password.");
                        break;

                    case 10:
                        System.out.print("Enter Employee name to search: ");
                        String name = sc.nextLine();
                        List<Employee> employeesByName = es.findEmployeesByName(name);
                        employeesByName.forEach(emp -> System.out.println(emp));
                        break;

                    case 11:
                        System.out.print("Enter Employee status to search: ");
                        String status = sc.nextLine();
                        List<Employee> employeesByStatus = es.findEmployeesByStatus(status);
                        employeesByStatus.forEach(emp -> System.out.println(emp));
                        break;

                    case 12:
                        System.out.print("Enter Employee role to search: ");
                        String role = sc.nextLine();
                        List<Employee> employeesByRole = es.getEmployeesByRole(role);
                        employeesByRole.forEach(emp -> System.out.println(emp));
                        break;

                    case 13:
                        int employeeCount = es.countEmployees();
                        System.out.println("Total number of employees: " + employeeCount);
                        break;

                    case 14:
                        System.out.println("Exiting Employee Management System. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct format.");
                sc.nextLine(); // clear buffer
            } catch (EmployeeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
}
