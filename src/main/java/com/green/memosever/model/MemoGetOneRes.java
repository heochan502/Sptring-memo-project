package com.green.memosever.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoGetOneRes {
    private String id;
    private String title;
    @JsonProperty("ctnts")
    private String content;
    @JsonProperty("created_at")
    private String createdAt;

}
