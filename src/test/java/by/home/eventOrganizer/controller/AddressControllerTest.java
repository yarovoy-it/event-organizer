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
public class AddressControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void testGetOneExist() throws Exception {
//        mockMvc.perform(get("/roles/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("ROLE_USER"))
//                .andReturn();
//    }
//
//    @Test
//    public void testGetOneNotExist() throws Exception {
//        mockMvc.perform(get("/roles/3"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Role doesn't exist!"))
//                .andReturn();
//    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/address"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("GRODNO"))
                .andExpect(jsonPath("$[0].street").value("Derzinskogo"))
                .andExpect(jsonPath("$[0].houseNumber").value("56"))
                .andExpect(jsonPath("$[1].city").value("GRODNO"))
                .andExpect(jsonPath("$[1].street").value("17may"))
                .andExpect(jsonPath("$[1].houseNumber").value("3"))
                .andReturn();
    }

    //    @Test
//    public void testPutOneBadRequest() throws Exception {
//        mockMvc.perform(put("/roles/1").contentType(APPLICATION_JSON_UTF8).content("{\"id\":2,\"name\":\"user1\"}"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Url param id is not equals to roleId!"))
//                .andReturn();
//    }
//
//    @Test
//    public void testPutOneExistBadRequest() throws Exception {
//        mockMvc.perform(put("/roles/1").contentType(APPLICATION_JSON_UTF8).content("{\"id\":1,\"name\":\"ROLE_ADMIN\"}"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Role name is not unique!"))
//                .andReturn();
//    }
//
//    @Test
//    public void testPutOneExist() throws Exception {
//        mockMvc.perform(put("/roles/2").contentType(APPLICATION_JSON_UTF8).content("{\"id\":2,\"name\":\"ROLE_USER\"}"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Role name is not unique!"))
//                .andReturn();
//    }
//
//    @Test
//    public void testPutOneNotExist() throws Exception {
//        mockMvc.perform(put("/roles/3").contentType(APPLICATION_JSON_UTF8).content("{\"id\":3,\"name\":\"user1\"}"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Role doesn't exist!"))
//                .andReturn();
//    }
//
    @Test
    public void testSaveExistBadRequest() throws Exception {
        mockMvc.perform(post("/address").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 1,\n" +
                "        \"city\": \"GRODNO\",\n" +
                "        \"houseNumber\": 15,\n" +
                "        \"apartment\": 23\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("{address.street.notEmpty};{address.street.notNull};"))
                .andReturn();
    }
//
//    @Test
//    public void testSaveNotExist() throws Exception {
//        mockMvc.perform(post("/roles").contentType(APPLICATION_JSON_UTF8).content("{\"name\":\"user2\"}"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("user2"))
//                .andReturn();
//    }
//
//    @Test
//    public void testDeleteExist() throws Exception {
//        mockMvc.perform(get("/roles/2"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void testDeleteNotExist() throws Exception {
//        mockMvc.perform(delete("/roles/3"))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.message").value("Role doesn't exist!"))
//                .andReturn();
//    }
}
