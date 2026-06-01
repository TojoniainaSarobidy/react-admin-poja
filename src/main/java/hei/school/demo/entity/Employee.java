package hei.school.demo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String department;
  private Double salary;
  private Boolean active;
}
