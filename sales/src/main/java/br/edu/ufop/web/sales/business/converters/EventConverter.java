package br.edu.ufop.web.sales.business.converters;

import br.edu.ufop.web.sales.controller.dtos.events.CreateEventDTO;
import br.edu.ufop.web.sales.controller.dtos.events.EventDTO;
import br.edu.ufop.web.sales.controller.dtos.events.UpdateEventDTO;
import br.edu.ufop.web.sales.enums.EnumEventType;
import br.edu.ufop.web.sales.infraestructure.entities.EventEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventConverter {
    public static EventDTO toDTO(EventEntity eventEntity) {
        return EventDTO.builder()
                .id(eventEntity.getId())
                .description(eventEntity.getDescription())
                .type(eventEntity.getType())
                .dateTime(eventEntity.getDateTime())
                .startingSales(eventEntity.getStartingSales())
                .price(eventEntity.getPrice())
                .createdAt(eventEntity.getCreatedAt())
                .updatedAt(eventEntity.getUpdatedAt())
                .build();
    }

    public static EventEntity toEntity(CreateEventDTO createEventDTO) {
        return EventEntity.builder()
                .description(createEventDTO.getDescription())
                .type(EnumEventType.getById(createEventDTO.getType()))
                .dateTime(createEventDTO.getDateTime())
                .startingSales(createEventDTO.getStartingSales())
                .endingSales(createEventDTO.getEndingSales())
                .price(createEventDTO.getPrice())
                .build();
    }

    public static void updateEntity(EventEntity eventEntity, UpdateEventDTO updateEventDTO) {
        if (updateEventDTO.getDescription() != null) {
            eventEntity.setDescription(updateEventDTO.getDescription());
        }
        if (updateEventDTO.getType() != null) {
            eventEntity.setType(EnumEventType.getById(updateEventDTO.getType()));
        }
        if (updateEventDTO.getDateTime() != null) {
            eventEntity.setDateTime(updateEventDTO.getDateTime());
        }
        if (updateEventDTO.getStartingSales() != null) {
            eventEntity.setStartingSales(updateEventDTO.getStartingSales());
        }
        if (updateEventDTO.getEndingSales() != null) {
            eventEntity.setEndingSales(updateEventDTO.getEndingSales());
        }
        if (updateEventDTO.getPrice() != null) {
            eventEntity.setPrice(updateEventDTO.getPrice());
        }
    }
}
