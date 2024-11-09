package Anudip.EmpManageSys.service;

import Anudip.EmpManageSys.exception.EmployeeException;
import Anudip.EmpManageSys.model.Employee;

import java.util.List;

public interface EmployeeService {

	    Employee createEmpAccount(Employee e) throws EmployeeException;
	    boolean deleteEmp(int eid) throws EmployeeException;
	    boolean updateEmp(Employee e) throws EmployeeException;
	    Employee findEmp(int eid) throws EmployeeException;
	    List<Employee> getAllEmployees() throws EmployeeException;
	    boolean validateEmployeeCredentials(String username, String password) throws EmployeeException;
	    int countEmployees() throws EmployeeException;
	    boolean updateEmployeeRole(int employeeId, String newRole) throws EmployeeException;
	    boolean updateEmployeeStatus(int employeeId, String status) throws EmployeeException;
	    boolean updateEmployeePassword(int employeeId, String newPassword) throws EmployeeException;
	    Employee getEmployeeById(int employeeId) throws EmployeeException;
	    List<Employee> findEmployeesByName(String name) throws EmployeeException;
	    List<Employee> findEmployeesByStatus(String status) throws EmployeeException;
		List<Employee> getEmployeesByRole(String role);
}