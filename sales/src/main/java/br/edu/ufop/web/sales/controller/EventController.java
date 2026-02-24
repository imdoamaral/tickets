package br.edu.ufop.web.sales.controller;

import br.edu.ufop.web.sales.business.services.EventService;
import br.edu.ufop.web.sales.controller.dtos.events.CreateEventDTO;
import br.edu.ufop.web.sales.controller.dtos.events.DeleteEventDTO;
import br.edu.ufop.web.sales.controller.dtos.events.EventDTO;
import br.edu.ufop.web.sales.controller.dtos.events.UpdateEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody CreateEventDTO createEventDTO) {
        return ResponseEntity.ok(eventService.create(createEventDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable(value = "id") UUID id) {
        try {
            EventDTO eventDTO = eventService.getByIdDTO(id);
            return ResponseEntity.ok(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(
            @PathVariable(value = "id") UUID id,
            @RequestBody UpdateEventDTO updateEventDTO) {
        try {
            EventDTO eventDTO = eventService.update(id, updateEventDTO);
            return ResponseEntity.ok(eventDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteEventDTO deleteEventDTO) {
        try {
            eventService.delete(deleteEventDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
