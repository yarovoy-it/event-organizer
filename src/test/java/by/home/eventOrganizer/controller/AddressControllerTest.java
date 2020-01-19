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

/**
 * The type Address controller test.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class AddressControllerTest {

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
     * Test get all.
     *
     * @throws Exception the exception
     */
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

    /**
     * Test save exist bad request.
     *
     * @throws Exception the exception
     */
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
                .andReturn();
    }

}
