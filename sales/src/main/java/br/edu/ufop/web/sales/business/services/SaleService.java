package br.edu.ufop.web.sales.business.services;

import br.edu.ufop.web.sales.business.converters.SaleConverter;
import br.edu.ufop.web.sales.controller.dtos.events.CreateSaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.DeleteSaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.SaleDTO;
import br.edu.ufop.web.sales.controller.dtos.events.UpdateSaleDTO;
import br.edu.ufop.web.sales.infraestructure.entities.EventEntity;
import br.edu.ufop.web.sales.infraestructure.entities.SaleEntity;
import br.edu.ufop.web.sales.infraestructure.repositories.ISaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public SaleDTO getById(UUID id) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));

        return SaleConverter.toDTO(saleEntity);
    }

    @Transactional
    public SaleDTO update(UUID id, UpdateSaleDTO updateSaleDTO) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));

        SaleConverter.updateEntity(saleEntity, updateSaleDTO);
        saleEntity = saleRepository.save(saleEntity);

        return SaleConverter.toDTO(saleEntity);
    }

    @Transactional
    public void delete(DeleteSaleDTO deleteSaleDTO) {
        SaleEntity saleEntity = saleRepository.findById(deleteSaleDTO.id())
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + deleteSaleDTO.id()));

        saleRepository.delete(saleEntity);
    }
}
