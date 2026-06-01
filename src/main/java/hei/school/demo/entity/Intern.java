package hei.school.demo.entity;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Intern {
  private Integer id;
  private List<Employee> employee;
  private String firstname;
  private String lastname;
  private String email;
  private String school;
  private Double amount;
}
