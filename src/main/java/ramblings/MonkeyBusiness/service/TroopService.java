package ramblings.MonkeyBusiness.service;

import org.springframework.stereotype.Service;
import ramblings.MonkeyBusiness.dto.TroopDTO;
import ramblings.MonkeyBusiness.repository.TroopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TroopService {
    private final TroopRepository troopRepository;
    private final TroopMapper troopMapper;

    public TroopService(TroopRepository troopRepository, TroopMapper troopMapper) {
        this.troopRepository = troopRepository;
        this.troopMapper = troopMapper;
    }

    public TroopDTO create(TroopDTO dto) {
        var troop = troopMapper.toTroop(dto);
        troopRepository.save(troop);
        return dto;
    }

    public List<TroopDTO> findAll() {
        return troopRepository.findAll()
                .stream()
                .map(troopMapper::totroopDTO)
                .collect(Collectors.toList());
    }
}
