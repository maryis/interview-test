package com.travix.medusa.busyflights.unitTests.busyFlights;

import com.travix.medusa.busyflights.controller.BusyFlightsController;
import com.travix.medusa.busyflights.controller.ToughJetController;
import com.travix.medusa.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.service.BusyFlightServ;
import com.travix.medusa.busyflights.service.ToughJetServ;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BusyFlightsController.class)
public class BusyFlightControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;

    @MockBean
    BusyFlightServ busyFlightServ;

    List<BusyFlightsResponse> list;
    int num;

    @Before
    public void initialize() {

        System.out.println("make sure the main app is up");

        list = new ArrayList<>();
        BusyFlightsResponse entity = new BusyFlightsResponse();
        entity.setFare(200.9);
        entity.setDepartureAirportCode("GGG");
        entity.setDestinationAirportCode("LLL");
        entity.setAirline("Tough");
        entity.setCabinclass("E");
        entity.setSupplier("ToughJet");
        list.add(entity);

    }

    @Test
    public void getFlights() {

        Mockito.when(busyFlightServ.findByParam(any())).thenReturn(list);

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
//                    .andDo(print())
                    .andExpect(jsonPath("$", hasSize(1)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFlightsBadParam() {


        Mockito.when(busyFlightServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/busy-flights-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "D1")//wrong
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("numberOfPassengers", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFlightsNoAuth() {


        Mockito.when(busyFlightServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/busy-flights-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("numberOfPassengers", "3")
                             )
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}