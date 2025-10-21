package com.omeracar.carventory.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004","kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005","tokenin surersi bitmistir"),
    USERNAME_NOT_FOUND("1006","username bulunamadi"),
    USERNAME_OR_PASSWORD_INVALID("1007","kullanici adi ya da sifre hatali"),
    REFRESH_TOKEN_NOT_FOUND("1008","refresh token bulunamadi"),
    REFRESH_TOKEN_IS_EXPIRED("1009","refresh token expired"),
    CURRENCY_RATES_IS_OCCURED("1010","doviz kuru alinamadi"),
    GENERAL_EXCEPTION("9999","genel bir hata oluştu");

    private String code;

    private String message;


    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
