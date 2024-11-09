package Anudip.EmpManageSys.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    
    @NotNull
    @NotEmpty(message = "Employee name cannot be empty")
    private String employeeName;
    
    @NotNull
    @Size(min = 2, max = 50)
    @Email(message = "Invalid email format")
    private String employeeUserName;

    private String password;
    private String status;
    private String role;		
}
