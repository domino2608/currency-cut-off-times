package com.dominikc.currencyraterest.controller;

import com.dominikc.currencyraterest.controller.response.CutOffTimeResponse;
import com.dominikc.currencyraterest.exception.ApiException;
import com.dominikc.currencyraterest.exception.CurrencyNotFoundException;
import com.dominikc.currencyraterest.model.CountryCutOffTime;
import com.dominikc.currencyraterest.model.CutOffTime;
import com.dominikc.currencyraterest.repository.CountryCutOffTimesRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Controller serving country cut off times.
 */
@RestController
@RequestMapping("/cut-off")
public class CountryCutOffController {

    private final CountryCutOffTimesRepository countryCutOffTimesRepository;

    public CountryCutOffController(CountryCutOffTimesRepository countryCutOffTimesRepository) {
        this.countryCutOffTimesRepository = countryCutOffTimesRepository;
    }

    @ApiOperation(value = "Get cut off time for given date and currency pair")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved cut off time"),
            @ApiResponse(code = 404, message = "Currency for provided ISO code not found"),
            @ApiResponse(code = 400, message = "Date in the past")
    })
    @GetMapping
    public CutOffTimeResponse cutOffTime(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         @RequestParam String currency1,
                                         @RequestParam String currency2) {

        LocalDate today = LocalDate.now();

        if (date.isBefore(today)) {
            throw new ApiException("Provided date cannot be in the past!");
        }

        CountryCutOffTime countryCutOffTime1 = countryCutOffTimesRepository.findOneByIsoCode(currency1.toUpperCase())
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found for provided code: " + currency1));

        CountryCutOffTime countryCutOffTime2 = countryCutOffTimesRepository.findOneByIsoCode(currency2.toUpperCase())
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found for provided code: " + currency2));

        int daysBetween = (int) DAYS.between(today, date);

        CutOffTime cutOffTimeCurrency1 = countryCutOffTime1.getCutOffTimeForDays(daysBetween);
        CutOffTime cutOffTimeCurrency2 = countryCutOffTime2.getCutOffTimeForDays(daysBetween);

        return new CutOffTimeResponse(cutOffTimeCurrency1.getEarlierCutOffTime(cutOffTimeCurrency2));
    }

}
