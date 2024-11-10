package dev.kjetil.query_by_example.employee;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private BigDecimal salary;

}
