package ramblings.MonkeyBusiness.service;

import org.springframework.stereotype.Service;
import ramblings.MonkeyBusiness.dto.MonkeyDTO;
import ramblings.MonkeyBusiness.dto.MonkeyResponseDTO;
import ramblings.MonkeyBusiness.model.Monkey;
import ramblings.MonkeyBusiness.model.Troop;

@Service
public class MonkeyMapper {
    public Monkey toMonkey(MonkeyDTO dto) {
        if (dto == null) {
            throw new NullPointerException("The Monkey DTO is null.");
        }
        var monkey = new Monkey();
        monkey.setName(dto.name());
        monkey.setRank(dto.rank());
        monkey.setStatus(dto.status());

        var troop = new Troop();
        troop.setId(dto.troopId());

        monkey.setTroop(troop);

        return monkey;
    }

    public MonkeyResponseDTO toMonkeyResponseDTO(Monkey monkey) {
        return new MonkeyResponseDTO(
                monkey.getName(),
                monkey.getRank(),
                monkey.getStatus()
        );
    }
}
