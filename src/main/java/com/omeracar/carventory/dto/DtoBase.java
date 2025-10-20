package com.omeracar.carventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class DtoBase {

    private Long id;

    private Date createTime;
}
