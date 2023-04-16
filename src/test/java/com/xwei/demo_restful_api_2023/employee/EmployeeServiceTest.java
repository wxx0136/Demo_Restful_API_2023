package com.xwei.demo_restful_api_2023.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployees() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8090/api/v1.0/Employee")
                .header("Authorization", "Bearer ********-****-****-****-************")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void addNewEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }
}