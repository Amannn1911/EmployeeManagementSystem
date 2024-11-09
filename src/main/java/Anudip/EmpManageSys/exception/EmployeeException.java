package Anudip.EmpManageSys.exception;

public class EmployeeException extends Exception {  // Change to checked exception

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Default constructor
    public EmployeeException() {
        super();
    }

    // Constructor that accepts a custom message
    public EmployeeException(String msg) {
        super(msg);
    }

    // Constructor that accepts a custom message and a cause
    public EmployeeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // Constructor that accepts a cause
    public EmployeeException(Throwable cause) {
        super(cause);
    }
}
