package Anudip.EmpManageSys.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import Anudip.EmpManageSys.dao.EmployeeDao;
import Anudip.EmpManageSys.exception.EmployeeException;
import Anudip.EmpManageSys.model.Employee;
import Anudip.EmpManageSys.utility.EMutil;
import Anudip.EmpManageSys.daoImpl.EmployeeDaoImpl;


public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean createEmpAccount(Employee e) throws EmployeeException {
        boolean isCreated = false;
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            isCreated = true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while creating employee account: " + ex.getMessage());
        } finally {
            em.close();
        }
        return isCreated;
    }

    @Override
    public boolean deleteEmp(int eid) throws EmployeeException {
        boolean isDeleted = false;
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, eid);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + eid + " not found.");
            }
            em.remove(employee);
            em.getTransaction().commit();
            isDeleted = true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while deleting employee: " + ex.getMessage());
        } finally {
            em.close();
        }
        return isDeleted;
    }

    @Override
    public boolean updateEmp(Employee e) throws EmployeeException {
        boolean isUpdated = false;
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee existingEmployee = em.find(Employee.class, e.getEmployeeId());
            if (existingEmployee == null) {
                throw new EmployeeException("Employee with ID " + e.getEmployeeId() + " not found.");
            }
            existingEmployee.setEmployeeName(e.getEmployeeName());
            existingEmployee.setEmployeeUserName(e.getEmployeeUserName());
            existingEmployee.setPassword(e.getPassword());
            existingEmployee.setStatus(e.getStatus());
            existingEmployee.setRole(e.getRole());

            em.merge(existingEmployee);
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while updating employee: " + ex.getMessage());
        } finally {
            em.close();
        }
        return isUpdated;
    }

    @Override
    public boolean findEmp(int eid) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            Employee employee = em.find(Employee.class, eid);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + eid + " not found.");
            }
            return true;
        } catch (NoResultException ex) {
            throw new EmployeeException("Employee with ID " + eid + " does not exist.");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            em.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while fetching all employees: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean validateEmployeeCredentials(String username, String password) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.employeeUserName = :username AND e.password = :password", Employee.class)
                                  .setParameter("username", username)
                                  .setParameter("password", password)
                                  .getSingleResult();
            em.getTransaction().commit();
            return employee != null;
        } catch (NoResultException ex) {
            em.getTransaction().rollback();
            return false;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while validating credentials: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public int countEmployees() throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            long count = (long) em.createQuery("SELECT COUNT(e) FROM Employee e").getSingleResult();
            em.getTransaction().commit();
            return (int) count;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while counting employees: " + ex.getMessage());
        } finally {
            em.close();
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
            em.getTransaction().rollback();
            throw new EmployeeException("Error while updating employee role: " + ex.getMessage());
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
            em.getTransaction().rollback();
            throw new EmployeeException("Error while updating employee status: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateEmployeePassword(int employeeId, String newPassword) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, employeeId);
            if (employee == null) {
                throw new EmployeeException("Employee with ID " + employeeId + " not found.");
            }
            employee.setPassword(newPassword);
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while updating employee password: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, employeeId);
            em.getTransaction().commit();

            if (employee == null) {
                throw new EmployeeException("Employee with ID " + employeeId + " not found.");
            }
            return employee;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while fetching employee by ID: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> findEmployeesByName(String name) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.employeeName LIKE :name", Employee.class)
                                         .setParameter("name", "%" + name + "%")
                                         .getResultList();
            em.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while finding employees by name: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> findEmployeesByStatus(String status) throws EmployeeException {
        EntityManager em = EMutil.providerEntityManager();
        try {
            em.getTransaction().begin();
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.status = :status", Employee.class)
                                         .setParameter("status", status)
                                         .getResultList();
            em.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new EmployeeException("Error while finding employees by status: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
