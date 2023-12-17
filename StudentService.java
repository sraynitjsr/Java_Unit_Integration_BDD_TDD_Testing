import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(String rollNumber, String updatedName) {
        Student student = studentRepository.findByRollNumber(rollNumber);
        if (student != null) {
            student.setName(updatedName);
            studentRepository.save(student);
        }
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
