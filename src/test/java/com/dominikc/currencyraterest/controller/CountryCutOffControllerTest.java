package com.dominikc.currencyraterest.controller;

import com.dominikc.currencyraterest.controller.response.CutOffTimeResponse;
import com.dominikc.currencyraterest.model.CountryCutOffTime;
import com.dominikc.currencyraterest.model.CutOffTime;
import com.dominikc.currencyraterest.repository.CountryCutOffTimesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryCutOffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private CountryCutOffController countryCutOffController;

    @Autowired
    private CountryCutOffTimesRepository countryCutOffTimesRepository;

    private static final String CURRENCY1_PARAM = "currency1";
    private static final String CURRENCY2_PARAM = "currency2";
    private static final String DATE_PARAM = "date";

    @Test
    public void contextLoads() {
        assertThat(countryCutOffController).isNotNull();
    }

    @Test
    public void should_return_bad_request_when_not_enough_parameters() throws Exception {
        this.mockMvc
                .perform(get("/cut-off"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(DATE_PARAM, LocalDate.now().toString())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(CURRENCY1_PARAM, "usd")
                                .param(CURRENCY2_PARAM, "pln")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void should_return_bad_request_for_date_in_past() throws Exception {
        LocalDate pastDate = LocalDate.now().minusDays(1);

        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(CURRENCY1_PARAM, "usd")
                                .param(CURRENCY2_PARAM, "pln")
                                .param(DATE_PARAM, pastDate.toString())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void should_return_not_found_on_wrong_currency() throws Exception {
        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(CURRENCY1_PARAM, "usd")
                                .param(CURRENCY2_PARAM, "test")
                                .param(DATE_PARAM, LocalDate.now().toString())
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(CURRENCY1_PARAM, "test")
                                .param(CURRENCY2_PARAM, "usd")
                                .param(DATE_PARAM, LocalDate.now().toString())
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void should_return_cut_off_time_for_currency_pair() throws Exception {
        String currency1 = "USD";
        String currency2 = "PLN";
        LocalDate today = LocalDate.now();

        CountryCutOffTime countryCutOffTimeUsd = countryCutOffTimesRepository.findOneByIsoCode(currency1).get();
        CountryCutOffTime countryCutOffTimePln = countryCutOffTimesRepository.findOneByIsoCode(currency2).get();

        CutOffTime earlierCutOffTimeToday =
                countryCutOffTimeUsd.getCutOffTimeForDays(0)
                        .getEarlierCutOffTime(countryCutOffTimePln.getCutOffTimeForDays(0));

        CutOffTimeResponse response = new CutOffTimeResponse(earlierCutOffTimeToday);

        this.mockMvc
                .perform(
                        get("/cut-off")
                                .param(CURRENCY1_PARAM, currency1)
                                .param(CURRENCY2_PARAM, currency2)
                                .param(DATE_PARAM, today.toString())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string(
                                equalTo(jacksonObjectMapper.writeValueAsString(response))
                        )
                );
    }

}
