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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class StaffControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSaveDuplicateAddress() throws Exception {
        mockMvc.perform(post("/staff").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 24,\n" +
                "        \"name\": \"testStaff\",\n" +
                "        \"surname\": \"testStaff\",\n" +
                "        \"phoneNumber\": 3752922223,\n" +
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
                .andExpect(jsonPath("$.message").value("Person with this address already exist!"))
                .andReturn();
    }

    @Test
    public void testSaveAddress() throws Exception {
        mockMvc.perform(post("/staff").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 24,\n" +
                "        \"name\": \"testStaff\",\n" +
                "        \"surname\": \"testStaff\",\n" +
                "        \"phoneNumber\": 3752922223,\n" +
                "        \"address\": {\n" +
                "            \"id\": 135,\n" +
                "            \"city\": \"GRODNO\",\n" +
                "            \"street\": \"TestStreet\",\n" +
                "            \"houseNumber\": 134,\n" +
                "            \"apartment\": 328\n" +
                "        },\n" +
                "        \"department\": \"PLANNER\",\n" +
                "        \"salary\": 40.5\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testStaff"))
                .andExpect(jsonPath("$.surname").value("testStaff"))
                .andExpect(jsonPath("$.phoneNumber").value("3752922223"))
                .andExpect(jsonPath("$.address.city").value("GRODNO"))
                .andExpect(jsonPath("$.address.street").value("TestStreet"))
                .andExpect(jsonPath("$.department").value("PLANNER"))
                .andExpect(jsonPath("$.salary").value("40.5"))
                .andReturn();
    }

    @Test
    public void testSaveNullAddress() throws Exception {
        mockMvc.perform(post("/staff").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 101,\n" +
                "        \"name\": \"testStaff\",\n" +
                "        \"surname\": \"testStaff\",\n" +
                "        \"phoneNumber\": 3752922222,\n" +
                "        \"department\": \"WAITER\",\n" +
                "        \"salary\": 99.9\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testStaff"))
                .andExpect(jsonPath("$.surname").value("testStaff"))
                .andExpect(jsonPath("$.phoneNumber").value("3752922222"))
                .andExpect(jsonPath("$.department").value("WAITER"))
                .andExpect(jsonPath("$.salary").value("99.9"))
                .andReturn();
    }

    @Test
    public void testGetAllNullAddress() throws Exception {
        mockMvc.perform(get("/staff"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name").value("Dasha"))
                .andExpect(jsonPath("$[1].surname").value("Mudilova"))
                .andExpect(jsonPath("$[1].phoneNumber").value("375297452387"))
                .andReturn();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/staff"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Vasia"))
                .andExpect(jsonPath("$[0].surname").value("Vasichkin"))
                .andExpect(jsonPath("$[0].address.id").value("2"))
                .andExpect(jsonPath("$[0].address.city").value("GRODNO"))
                .andExpect(jsonPath("$[0].address.street").value("Derzinskogo"))
                .andExpect(jsonPath("$[0].address.houseNumber").value("56"))
                .andExpect(jsonPath("$[0].address.apartment").value("16"))
                .andExpect(jsonPath("$[0].phoneNumber").value("375297642865"))
                .andReturn();
    }

    @Test
    public void testGetByDepartment() throws Exception {
        mockMvc.perform(get("/staff/dep/waiter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dasha"))
                .andExpect(jsonPath("$[0].surname").value("Mudilova"))
                .andExpect(jsonPath("$[0].phoneNumber").value("375297452387"))
                .andExpect(jsonPath("$[0].department").value("WAITER"))
                .andReturn();
    }
}
