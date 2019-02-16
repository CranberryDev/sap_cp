package com.artem_agayev.sapcp.dto;

import com.artem_agayev.sapcp.dto.inners.TemperatureInnerMain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDTO {

    TemperatureInnerMain main;

}
