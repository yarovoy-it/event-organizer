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
 * The type Order controllers test.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class})
@WebAppConfiguration
@Transactional
public class OrderControllersTest {

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
     * Test save.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/order").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 999,\n" +
                "        \"goods\": [\n" +
                "            {\n" +
                "                \"id\": 201,\n" +
                "                \"name\": \"Hultafors\",\n" +
                "                \"type\": \"Knife\",\n" +
                "                \"count\": 500,\n" +
                "                \"price\": 0.65\n" +
                "            }\n" +
                "        ],\n" +
                "        \"beverages\": [\n" +
                "            {\n" +
                "                \"id\": 202,\n" +
                "                \"name\": \"Patron\",\n" +
                "                \"type\": \"Tequila\",\n" +
                "                \"count\": 90,\n" +
                "                \"price\": 120.0,\n" +
                "                \"volume\": 1.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 203,\n" +
                "                \"name\": \"Highland Park\",\n" +
                "                \"type\": \"Whisky\",\n" +
                "                \"count\": 85,\n" +
                "                \"price\": 100.0,\n" +
                "                \"volume\": 1.0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"staff\": [\n" +
                "            {\n" +
                "                \"id\": 204,\n" +
                "                \"name\": \"Macha\",\n" +
                "                \"surname\": \"Waiterovna\",\n" +
                "                \"phoneNumber\": 375335673422,\n" +
                "                \"department\": \"WAITER\",\n" +
                "                \"salary\": 20.9\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 205,\n" +
                "                \"name\": \"Grisha\",\n" +
                "                \"surname\": \"Bikov\",\n" +
                "                \"phoneNumber\": 375297272387,\n" +
                "                \"department\": \"SECURITY\",\n" +
                "                \"salary\": 25.0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"customer\": {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"Dima\",\n" +
                "            \"surname\": \"Gurtov\",\n" +
                "            \"phoneNumber\": 375337452798,\n" +
                "            \"address\": {\n" +
                "                \"id\": 51,\n" +
                "                \"city\": \"GRODNO\",\n" +
                "                \"street\": \"Folush\",\n" +
                "                \"houseNumber\": 137,\n" +
                "                \"apartment\": 10\n" +
                "            },\n" +
                "            \"discount\": 0\n" +
                "        },\n" +
                "        \"description\": \"nice and sexy\",\n" +
                "        \"executeDate\": \"2020-01-12\",\n" +
                "        \"address\": {\n" +
                "            \"id\": 555,\n" +
                "            \"city\": \"GRODNO\",\n" +
                "            \"street\": \"Pushkwina\",\n" +
                "            \"houseNumber\": 435,\n" +
                "            \"apartment\": 1445\n" +
                "        }\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("106"))
                .andExpect(jsonPath("$.address.city").value("GRODNO"))
                .andExpect(jsonPath("$.address.street").value("Pushkwina"))
                .andReturn();
    }

    /**
     * Test save customer not exist.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSaveCustomerNotExist() throws Exception {
        mockMvc.perform(post("/order").contentType(APPLICATION_JSON_UTF8).content("{\n" +
                "        \"id\": 999,\n" +
                "        \"goods\": [\n" +
                "            {\n" +
                "                \"id\": 201,\n" +
                "                \"name\": \"Hultafors\",\n" +
                "                \"type\": \"Knife\",\n" +
                "                \"count\": 500,\n" +
                "                \"price\": 0.65\n" +
                "            }\n" +
                "        ],\n" +
                "        \"beverages\": [\n" +
                "            {\n" +
                "                \"id\": 202,\n" +
                "                \"name\": \"Patron\",\n" +
                "                \"type\": \"Tequila\",\n" +
                "                \"count\": 90,\n" +
                "                \"price\": 120.0,\n" +
                "                \"volume\": 1.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 203,\n" +
                "                \"name\": \"Highland Park\",\n" +
                "                \"type\": \"Whisky\",\n" +
                "                \"count\": 85,\n" +
                "                \"price\": 100.0,\n" +
                "                \"volume\": 1.0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"staff\": [\n" +
                "            {\n" +
                "                \"id\": 204,\n" +
                "                \"name\": \"Macha\",\n" +
                "                \"surname\": \"Waiterovna\",\n" +
                "                \"phoneNumber\": 375335673422,\n" +
                "                \"department\": \"WAITER\",\n" +
                "                \"salary\": 20.9\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 205,\n" +
                "                \"name\": \"Grisha\",\n" +
                "                \"surname\": \"Bikov\",\n" +
                "                \"phoneNumber\": 375297272387,\n" +
                "                \"department\": \"SECURITY\",\n" +
                "                \"salary\": 25.0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"customer\": {\n" +
                "            \"id\": 1001,\n" +
                "            \"name\": \"Viktor\",\n" +
                "            \"surname\": \"Melnik\",\n" +
                "            \"phoneNumber\": 375337468215,\n" +
                "            \"address\": {\n" +
                "                \"id\": 51,\n" +
                "                \"city\": \"GRODNO\",\n" +
                "                \"street\": \"OrdDerzinskogo\",\n" +
                "                \"houseNumber\": 205,\n" +
                "                \"apartment\": 74\n" +
                "            },\n" +
                "            \"discount\": 0\n" +
                "        },\n" +
                "        \"description\": \"nice and sexy\",\n" +
                "        \"executeDate\": \"2020-01-12\",\n" +
                "        \"address\": {\n" +
                "            \"id\": 555,\n" +
                "            \"city\": \"GRODNO\",\n" +
                "            \"street\": \"Pushkina\",\n" +
                "            \"houseNumber\": 45,\n" +
                "            \"apartment\": 145\n" +
                "        }\n" +
                "    }"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("Customer doesn't exist!"))
                .andReturn();
    }

    /**
     * Test get all.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}
