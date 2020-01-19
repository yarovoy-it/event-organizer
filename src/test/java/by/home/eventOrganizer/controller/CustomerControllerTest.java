package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.configuration.TestWebConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Customer controller test.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class CustomerControllerTest {

    /**
     * The constant APPLICATION_JSON_UTF8.
     */
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Test save duplicate phone.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSaveDuplicatePhone() throws Exception {
        mockMvc.perform(post("/customer").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 24,\n" +
                "        \"name\": \"Mania\",\n" +
                "        \"surname\": \"Golovach\",\n" +
                "        \"phoneNumber\": 375337832475,\n" +
                "        \"address\": {\n" +
                "            \"id\": 5,\n" +
                "            \"city\": \"GRODNO\",\n" +
                "            \"street\": \"Pushkina\",\n" +
                "            \"houseNumber\": 34,\n" +
                "            \"apartment\": 28\n" +
                "        },\n" +
                "        \"department\": \"PLANNER\",\n" +
                "        \"salary\": 40.5\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").isEmpty())
                .andReturn();
    }

    /**
     * Test save address.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSaveAddress() throws Exception {
        mockMvc.perform(post("/customer").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 24,\n" +
                "        \"name\": \"testCustomer\",\n" +
                "        \"surname\": \"testCustomer\",\n" +
                "        \"phoneNumber\": 3752922225,\n" +
                "        \"address\": {\n" +
                "            \"id\": 135,\n" +
                "            \"city\": \"GRODNO\",\n" +
                "            \"street\": \"TestStreet\",\n" +
                "            \"houseNumber\": 134,\n" +
                "            \"apartment\": 328\n" +
                "        },\n" +
                "        \"discount\": 0.0,\n" +
                "        \"salary\": 40.5\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testCustomer"))
                .andExpect(jsonPath("$.surname").value("testCustomer"))
                .andExpect(jsonPath("$.phoneNumber").value("3752922225"))
                .andExpect(jsonPath("$.address.city").value("GRODNO"))
                .andExpect(jsonPath("$.address.street").value("TestStreet"))
                .andExpect(jsonPath("$.discount").value("0"))
                .andReturn();
    }
}
