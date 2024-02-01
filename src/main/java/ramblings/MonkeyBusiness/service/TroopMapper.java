package ramblings.MonkeyBusiness.service;

import org.springframework.stereotype.Service;
import ramblings.MonkeyBusiness.dto.TroopDTO;
import ramblings.MonkeyBusiness.model.Troop;

@Service
public class TroopMapper {
    public Troop toTroop(TroopDTO dto) {
        return new Troop(dto.name());
    }

    public TroopDTO totroopDTO(Troop troop) {
        return new TroopDTO(troop.getName());
    }

}
