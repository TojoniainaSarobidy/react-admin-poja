package hei.school.demo.service;

import hei.school.demo.entity.Intern;
import hei.school.demo.repository.InternRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InternService {

  private final InternRepository internRepository;

  public List<Intern> interns() {
    return internRepository.getAllInterns();
  }

  public Intern createIntern(Intern intern, int idEmployee) {
    return internRepository.createIntern(intern, idEmployee);
  }

  public Intern updateIntern(Intern intern, int idEmployee) {
    return internRepository.updateIntern(intern, idEmployee);
  }

  public String deleteIntern(int idIntern) {
    return internRepository.deleteIntern(idIntern);
  }
}
