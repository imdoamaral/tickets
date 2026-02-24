package br.edu.ufop.web.sales.controller.dtos.events;

import br.edu.ufop.web.sales.enums.EnumSaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSaleDTO {
    private EnumSaleStatus status;
}
