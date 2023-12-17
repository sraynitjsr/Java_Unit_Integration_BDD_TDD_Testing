import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateStudent_shouldUpdateStudentName_whenStudentExists() {
        // Arrange
        String rollNumber = "123";
        String updatedName = "UpdatedName";
        Student existingStudent = new Student("OriginalName", rollNumber);
        
        // Mock the repository behavior
        when(studentRepository.findByRollNumber(rollNumber)).thenReturn(existingStudent);

        // Act
        studentService.updateStudent(rollNumber, updatedName);

        // Assert
        verify(studentRepository, times(1)).findByRollNumber(rollNumber);
        verify(studentRepository, times(1)).save(existingStudent);

        // Assert that the student's name is updated
        assert(existingStudent.getName().equals(updatedName));
    }

    @Test
    void updateStudent_shouldNotUpdateStudent_whenStudentDoesNotExist() {
        // Arrange
        String rollNumber = "123";
        String updatedName = "UpdatedName";

        // Mock the repository behavior
        when(studentRepository.findByRollNumber(rollNumber)).thenReturn(null);

        // Act
        studentService.updateStudent(rollNumber, updatedName);

        // Assert
        verify(studentRepository, times(1)).findByRollNumber(rollNumber);
        verify(studentRepository, never()).save(any());
    }
}
