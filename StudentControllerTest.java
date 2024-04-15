import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents() throws Exception {
        // Arrange
        List<Student> students = Arrays.asList(new Student("1", "John"), new Student("2", "Alice"));
        when(studentService.getAllStudents()).thenReturn(students);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentById() throws Exception {
        // Arrange
        Student student = new Student("1", "John");
        when(studentService.getStudentById("1")).thenReturn(Optional.of(student));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
    @Test
    public void testAddStudent() throws Exception {
        // Arrange
        Student student = new Student("1", "John");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .content("{\"id\":\"1\", \"name\":\"John\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Arrange

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/students/1?updatedName=John")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        // Arrange

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
