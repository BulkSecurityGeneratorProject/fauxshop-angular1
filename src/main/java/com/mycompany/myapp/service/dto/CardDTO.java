package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by derekzuk on 10/10/17.
 */
public class CardDTO {

    @JsonProperty("number")
    private Long number;

    @JsonProperty("expMonth")
    private Long expMonth;

    @JsonProperty("expYear")
    private Long expYear;

    @JsonProperty("cvc")
    private Long cvc;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Long expMonth) {
        this.expMonth = expMonth;
    }

    public Long getExpYear() {
        return expYear;
    }

    public void setExpYear(Long expYear) {
        this.expYear = expYear;
    }

    public Long getCvc() {
        return cvc;
    }

    public void setCvc(Long cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
            "number=" + number +
            ", expMonth=" + expMonth +
            ", expYear=" + expYear +
            ", cvc=" + cvc +
            '}';
    }
}
