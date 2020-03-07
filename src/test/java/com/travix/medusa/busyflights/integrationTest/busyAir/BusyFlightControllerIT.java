package com.travix.medusa.busyflights.integrationTest.busyAir;

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

public class BusyFlightControllerIT {

    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetFlights() {
        try {
            mockMvc.perform(
                    get("/busy-flights-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("numberOfPassengers", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(20)))
                    .andExpect(jsonPath("$[0].carrier", is("Tough")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetFlightsNoAuth() {
        try {
            mockMvc.perform(
                    get("/busy-flights-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("numberOfPassengers", "3")
            )                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFlightsBadParam() {
        try {
            mockMvc.perform(
                    get("/busy-flights-management/flight")
                            .param("origin", "DP331")//wrong length
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("numberOfPassengers", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}