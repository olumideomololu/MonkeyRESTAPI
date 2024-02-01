package ramblings.MonkeyBusiness.controller;

import org.springframework.web.bind.annotation.*;
import ramblings.MonkeyBusiness.dto.TroopDTO;
import ramblings.MonkeyBusiness.model.Troop;
import ramblings.MonkeyBusiness.repository.TroopRepository;
import ramblings.MonkeyBusiness.service.TroopService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TroopController {
    private final TroopService troopService;

    public TroopController(TroopService troopService) {
        this.troopService = troopService;
    }

    @PostMapping("/troops")
    public TroopDTO create(@RequestBody TroopDTO dto) {
        return troopService.create(dto);
    }

    @GetMapping("/troops")
    public List<TroopDTO> findAll() {
        return troopService.findAll();
    }
}
