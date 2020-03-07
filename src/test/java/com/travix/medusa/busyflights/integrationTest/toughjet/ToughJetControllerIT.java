package com.travix.medusa.busyflights.integrationTest.toughjet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ToughJetControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;

    @Test
    public void testGetFlights() {
        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inboundDate", "2019-03-05")
                            .param("outboundDate", "2019-03-04")
                            .param("numberOfAdults", "3")
                            .with(httpBasic(username, password)))

                    .andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$", hasSize(10)))
                    .andExpect(jsonPath("$[0].carrier", is("Tough")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFlightWithoutAuth() {
        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inboundDate", "2019-03-05")
                            .param("outboundDate", "2019-03-04")
                            .param("numberOfAdults", "3")
            )
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFlightWrongInput() {
        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP155")//wrong
                            .param("to", "DA1")
                            .param("inboundDate", "2019-03-05")
                            .param("outboundDate", "2019-0-04")//wrong
                            .param("numberOfAdults", "3")
                    .with(httpBasic(username,password))
            )
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}