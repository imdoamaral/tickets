package br.edu.ufop.web.sales.business.services;

import br.edu.ufop.web.sales.business.converters.SaleConverter;
import br.edu.ufop.web.sales.controller.dtos.events.CreateSaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.SaleDTO;
import br.edu.ufop.web.sales.infraestructure.entities.EventEntity;
import br.edu.ufop.web.sales.infraestructure.entities.SaleEntity;
import br.edu.ufop.web.sales.infraestructure.repositories.ISaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final ISaleRepository saleRepository;
    private final EventService eventService;

    public List<SaleDTO> getAll() {
        List<SaleEntity> saleEntityList = saleRepository.findAll();

        return saleEntityList.stream()
                .map(SaleConverter::toDTO)
                .toList();
    }

    @Transactional
    public SaleDTO create(CreateSaleDTO createSaleDTO) {
        SaleEntity saleEntity = SaleConverter.toEntity(createSaleDTO);

        Optional<EventEntity> eventEntityOptional = eventService.getById(createSaleDTO.getEventId());

        if (eventEntityOptional.isEmpty()) {
            throw new RuntimeException("Event does not exist.");
        }

        saleEntity.setEvent(eventEntityOptional.get());

        saleRepository.save(saleEntity);
        return SaleConverter.toDTO(saleEntity);
    }
}
