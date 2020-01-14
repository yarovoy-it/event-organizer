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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class GoodsControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetOneExist() throws Exception {
        mockMvc.perform(get("/goods/28"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Plastic"))
                .andExpect(jsonPath("$.type").value("Table"))
                .andExpect(jsonPath("$.count").value("100"))
                .andReturn();
    }

    @Test
    public void testGetOneNotExist() throws Exception {
        mockMvc.perform(get("/goods/43"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("Goods doesn't exist!"))
                .andReturn();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/goods"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Plastic"))
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

    @Test
    public void testPutOneNotExist() throws Exception {
        mockMvc.perform(put("/goods/43").contentType(APPLICATION_JSON_UTF8).content("{\"id\":43,\"name\":\"Plastic\",\"type\":\"Fork\"}"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("error.goods.NotExist"))
                .andReturn();
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/goods").contentType(APPLICATION_JSON_UTF8).content("{\"id\": 301,\"name\": \"testHultafors\", \"type\": \"testKnife\",\"count\": 500, \"price\": 0.65}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testHultafors"))
                .andExpect(jsonPath("$.type").value("testKnife"))
                .andExpect(jsonPath("$.count").value("500"))
                .andExpect(jsonPath("$.price").value("0.65"))
                .andReturn();
    }
}
