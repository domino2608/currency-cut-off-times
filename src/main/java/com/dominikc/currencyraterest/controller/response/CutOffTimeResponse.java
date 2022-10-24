package com.dominikc.currencyraterest.controller.response;

import com.dominikc.currencyraterest.model.CutOffTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Cut off time response for the request
 */
@Data
public class CutOffTimeResponse {

    @JsonProperty("cutOffTime")
    private String time;

    public CutOffTimeResponse(CutOffTime cutOffTime) {
        this.time = cutOffTime.toString();
    }
}
