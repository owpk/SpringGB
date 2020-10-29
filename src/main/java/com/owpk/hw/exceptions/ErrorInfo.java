package com.owpk.hw.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
public class ErrorInfo {

    private String cause;
    private StringBuffer url;
    private Date timestamp;

    public ErrorInfo(StringBuffer sb, Exception cause) {
        this.cause = cause.getMessage();
        this.url = sb;
        this.timestamp = new Date();
    }
}
