package Anudip.EmpManageSys.serviceImpl;

import Anudip.EmpManageSys.exception.EmployeeException;
import Anudip.EmpManageSys.model.Employee;
import Anudip.EmpManageSys.service.EmployeeService;
import Anudip.EmpManageSys.dao.EmployeeDao;
import Anudip.EmpManageSys.daoImpl.EmployeeDaoImpl;
import Anudip.EmpManageSys.serviceImpl.EmployeeServiceImpl;
import Anudip.EmpManageSys.utility.EMutil;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Override
    public Employee createEmpAccount(Employee employee) throws EmployeeException {
        try {
            boolean isCreated = dao.createEmpAccount(employee);
            if (!isCreated) {
                throw new EmployeeException("Employee account creation failed.");
            }
            return employee;
        } catch (Exception ex) {
            throw new EmployeeException("Failed to create employee account: " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteEmp(int employeeId) throws EmployeeException {
        try {
            boolean isDeleted = dao.deleteEmp(employeeId);
            if (!isDeleted) {
                throw new EmployeeException("Failed to delete employee with ID: " + employeeId);
            }
            return true;
        } catch (Exception ex) {
            throw new EmployeeException("Failed to delete employee with ID: " + employeeId + ". Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean updateEmp(Employee employee) throws EmployeeException {
        try {
            boolean isUpdated = dao.updateEmp(employee);
            if (!isUpdated) {
                throw new EmployeeException("Employee update failed.");
            }
            return true;
        } catch (Exception ex) {
            throw new EmployeeException("Failed to update employee: " + ex.getMessage());
        }
    }

    @Override
    public Employee findEmp(int employeeId) throws EmployeeException {
        try {
            Employee employee = dao.getEmployeeById(employeeId);  // Assuming 'dao' is correctly set up
            if (employee == null) {
                throw new EmployeeException("Employee not found with ID: " + employeeId);
            }
            return employee;
        } catch (Exception ex) {
            throw new EmployeeException("Failed to find employee with ID: " + employeeId + ". Error: " + ex.getMessage());
        }
    }


    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        try {
            return dao.getAllEmployees();
        } catch (Exception ex) {
            throw new EmployeeException("Failed to retrieve all employees. Error: " + ex.getMessage());
        }
    }
    public boolean deleteEmp1(int employeeId) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, employeeId);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + employeeId + " not found.");
            }
            em.remove(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new EmployeeException("Failed to delete employee with ID " + employeeId + ": " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    @Override
    public List<Employee> findEmployeesByName(String name) throws EmployeeException {
        try {
            return dao.findEmployeesByName(name);
        } catch (Exception ex) {
            throw new EmployeeException("Failed to find employees by name: " + name + ". Error: " + ex.getMessage());
        }
    }


    @Override
    public List<Employee> findEmployeesByStatus(String status) throws EmployeeException {
        try {
            return dao.findEmployeesByStatus(status);
        } catch (Exception ex) {
            throw new EmployeeException("Failed to find employees by status: " + status + ". Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean validateEmployeeCredentials(String username, String password) throws EmployeeException {
        try {
            return dao.validateEmployeeCredentials(username, password);
        } catch (Exception ex) {
            throw new EmployeeException("Failed to validate employee credentials. Error: " + ex.getMessage());
        }
    }

    @Override
    public int countEmployees() throws EmployeeException {
        try {
            return dao.countEmployees();
        } catch (Exception ex) {
            throw new EmployeeException("Failed to count employees. Error: " + ex.getMessage());
        }
    }


    @Override
    public boolean updateEmployeePassword(int employeeId, String newPassword) throws EmployeeException {
        try {
            return dao.updateEmployeePassword(employeeId, newPassword);
        } catch (Exception ex) {
            throw new EmployeeException("Failed to update password for employee with ID: " + employeeId + ". Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean updateEmployeeRole(int employeeId, String newRole) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, employeeId);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + employeeId + " not found.");
            }
            employee.setRole(newRole);
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new EmployeeException("Failed to update role for employee with ID: " + employeeId);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateEmployeeStatus(int employeeId, String status) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, employeeId);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + employeeId + " not found.");
            }
            employee.setStatus(status);
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new EmployeeException("Failed to update status for employee with ID: " + employeeId);
        } finally {
            em.close();
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        // Get the entity manager
        EntityManager em = EMutil.providerEntityManager();

        try {
            // Find employee by ID
            Employee employee = em.find(Employee.class, employeeId);

            if (employee == null) {
                throw new EmployeeException("Employee not found with ID: " + employeeId);
            }

            return employee;
        } catch (Exception e) {
            throw new EmployeeException("Error while fetching employee by ID: " + e.getMessage(), e);
        } finally {
            em.close();  // Always close the EntityManager
        }
    }

    @Override
    public List<Employee> getEmployeesByRole(String role) {
        // Get the entity manager
        EntityManager em = EMutil.providerEntityManager();
        List<Employee> employees = null;

        try {
            // Create a JPQL query to get employees by role
            String jpql = "SELECT e FROM Employee e WHERE e.role = :role";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("role", role);

            // Execute query and return results
            employees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();  // Always close the EntityManager
        }

        return employees;
    }


}
