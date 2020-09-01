package com.dj.sometest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Chris
 * @Date: 2020/7/29 21:50
 */
@Data
public class Progress {

    @JsonProperty("Total")
    private Integer Total;

    @JsonProperty("Success")
    private String Success;
}
