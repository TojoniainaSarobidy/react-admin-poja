package hei.school.demo.endpoint.rest.controller.health;

import hei.school.demo.entity.Intern;
import hei.school.demo.service.InternService;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InternController {

    private final InternService internService;

    @GetMapping(value = "/interns")
    public ResponseEntity<?> interns() {
        List<Intern> result = internService.interns();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }

    @PostMapping(value = "/intern/{idEmployee}")
    public ResponseEntity<?> createIntern(@RequestBody Intern intern, @PathVariable int idEmployee) {
        Intern result = internService.createIntern(intern, idEmployee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(result);
    }
}
