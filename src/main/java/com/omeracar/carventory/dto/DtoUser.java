package com.omeracar.carventory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser extends DtoBase{

    private String username;

    private String password;


}
