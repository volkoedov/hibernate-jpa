package vea.home.entities;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus status;

    public Employee(String employeeId, String name, EmployeeStatus status) {
        this.employeeId = employeeId;
        this.name = name;
        this.status = status;
    }

    protected Employee() {
    }

    public EmployeeStatus getStatus() {
        return status;
    }
}
