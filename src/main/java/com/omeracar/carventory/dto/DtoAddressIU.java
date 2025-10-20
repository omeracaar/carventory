package com.omeracar.carventory.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DtoAddressIU {

    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private String street;

}
