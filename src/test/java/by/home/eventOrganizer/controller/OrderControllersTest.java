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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class OrderControllersTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].goods").value("Plastic"))
                .andExpect(jsonPath("$[0].type").value("Table"))
                .andExpect(jsonPath("$[0].count").value("100"))
                .andExpect(jsonPath("$[1].name").value("Hultafors"))
                .andExpect(jsonPath("$[1].type").value("Knife"))
                .andExpect(jsonPath("$[1].count").value("500"))
                .andExpect(jsonPath("$[2].name").value("King"))
                .andExpect(jsonPath("$[2].type").value("Knife"))
                .andExpect(jsonPath("$[2].count").value("500"))
                .andReturn();
    }
}
