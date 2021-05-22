package com.vector;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test1_shouldHandlePostRequest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("email","firstName@email.com");
        json.put("password","123");
        json.put("firstName","firstName");
        json.put("lastName","lastName");
        RequestBuilder request = post("/user")
                .header("Content-Type","application/json")
                .content(json.toString());

        mockMvc.perform(request)
                .andExpect(status().isCreated());    //new user

        mockMvc.perform(request)
                .andExpect(status().isConflict());  //the user exists

        json = new JSONObject();                    //new user
        json.put("email","lastName@email.com");
        json.put("password","456");
        json.put("firstName","firstName2");
        json.put("lastName","lastName2");
        request = post("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isCreated());

        json.put("password","");             //password null
        request = post("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

        json.put("email","");               //email null
        json.put("password","123");
        request = post("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test2_shouldHandleGetRequest() throws Exception {

        RequestBuilder request = get("/user/firstName@email.com");
        mockMvc.perform(request)
                .andExpect(status().isOk());

        request = get("/user/lastName@email.com");
        mockMvc.perform(request)
                .andExpect(status().isOk());

        request = get("/user/firstName1@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());

        request = get("/user/lastName2@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void test3_shouldHandlePutRequest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("email","firstName1@email.com");
        json.put("password","1234");
        json.put("firstName","firstName3");
        json.put("lastName","lastName3");
        RequestBuilder request = put("/user")
                .header("Content-Type","application/json")
                .content(json.toString());

        mockMvc.perform(request)
                .andExpect(status().isNotFound());    //the user not exists

        json.put("email","firstName@email.com");  //the user exists
        request = put("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isNoContent());

        json.put("password","");  //password null
        request = put("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

        json.put("email","");  //email null
        json.put("password","12345");
        request = put("/user")
                .header("Content-Type","application/json")
                .content(json.toString());
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test4_shouldHandleDeleteRequest() throws Exception {

        RequestBuilder request = delete("/user/firstName3@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());

        request = delete("/user/lastName3@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());

        request = delete("/user/firstName@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNoContent());

        request = delete("/user/lastName@email.com");
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
