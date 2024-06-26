package in.makersharks;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest(classes = Application.class)
class ApplicationTests {

	  @MockBean
	    private Application mockApplication; // Mocking the Application class if needed

	    @Test
	    public void contextLoads() {
	        // This test checks if the Spring context loads successfully
	        assertNotNull(mockApplication);
	    }
	
	

}
