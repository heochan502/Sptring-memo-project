package com.green.memosever.config.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//@Setter
@Getter
//@Builder
@AllArgsConstructor
public class ResultResponse<T> {
    private String resultMessage;
    private T resultData;
}
