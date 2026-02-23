package br.edu.ufop.web.sales.controller.dtos.events;

import br.edu.ufop.web.sales.enums.EnumSalesStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private UUID id;
    private UUID user_id;
    private EventDTO event;
    private LocalDateTime dateTime;
    private EnumSalesStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
