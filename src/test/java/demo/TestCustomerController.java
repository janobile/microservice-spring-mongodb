package demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CustomerController;
import com.example.demo.repository.CustomerRepository;

public class TestCustomerController {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Mock
    private CustomerRepository customerRepository;
 
    @InjectMocks
    private CustomerController customerController;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
 
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
 
    }
    
    @Test
    public void testcreateCustomer() throws Exception {
     
    	String url = "/customers/";
    	
    	// Creamos el customer 1
    	String name = "Juliana Nobile";
    	String email = "julianaanobile@gmail.com";
    	String description = "Full Stack Software Developer";
        String requestJson = createCustomerJson(name, email, description);
        
        ResultMatcher expected1 = MockMvcResultMatchers.jsonPath("name").value(name);
        ResultMatcher expected2 = MockMvcResultMatchers.jsonPath("email").value(email);
        ResultMatcher expected3 = MockMvcResultMatchers.jsonPath("description").value(description);
        
        this.mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(expected1)
                .andExpect(expected2)
                .andExpect(expected3);
    }
    
    @Test
    public void testlistAllCustomers() throws Exception {
     
    	String url = "/customers/";
    	
    	// Creamos el customer 1
    	String name = "Customer 1";
    	String email = "customer1@example.com";
    	String description = "Description Customer 1";
        String requestJson = createCustomerJson(name, email, description);
        
        ResultMatcher expected1 = MockMvcResultMatchers.jsonPath("name").value(name);
        ResultMatcher expected2 = MockMvcResultMatchers.jsonPath("email").value(email);
        ResultMatcher expected3 = MockMvcResultMatchers.jsonPath("description").value(description);
        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(requestJson);
        
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(expected1)
                .andExpect(expected2)
                .andExpect(expected3);
        
		this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(expected1)
                .andExpect(expected2)
                .andExpect(expected3);
        
        // Test list all
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    
    private static String createCustomerJson (String name, String email, String description) {
        return "{ \"name\": \"" + name + "\", " +
                            "\"email\":\"" + email + "\"," +
                            "\"description\":\"" + description + "\"}";
    }
    
}