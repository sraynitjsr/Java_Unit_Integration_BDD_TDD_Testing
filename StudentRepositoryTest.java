import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        // Mocking the behavior of the repository
        Student student = new Student();
        student.setRollNumber("12345");
        when(studentRepository.findByRollNumber("12345")).thenReturn(student);
        when(studentRepository.findByRollNumber("nonExistentRollNumber")).thenReturn(null);
    }

    @Test
    public void testFindByExistingRollNumber() {
        // Arrange
        String existingRollNumber = "12345";

        // Act
        Student student = studentRepository.findByRollNumber(existingRollNumber);

        // Assert
        assertEquals(existingRollNumber, student.getRollNumber());
    }

    @Test
    public void testFindByNonExistingRollNumber() {
        // Arrange
        String nonExistingRollNumber = "nonExistentRollNumber";

        // Act
        Student student = studentRepository.findByRollNumber(nonExistingRollNumber);

        // Assert
        assertEquals(null, student);
    }
}
