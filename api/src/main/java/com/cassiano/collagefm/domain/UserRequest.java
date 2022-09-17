package com.cassiano.collagefm.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserRequest {

    @Getter
    @JsonProperty("username")
    private String username;
    @JsonProperty("period")
    private String period;
    @JsonProperty("limit")
    private Integer limit;

    public String getFormatedParams(){
        return "&username="+username+"&period="+period+"&limit="+limit;
    }

}
