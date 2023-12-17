import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/{rollNumber}")
    public void updateStudent(@PathVariable String rollNumber, @RequestParam String updatedName) {
        studentService.updateStudent(rollNumber, updatedName);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
