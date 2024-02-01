package ramblings.MonkeyBusiness.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ramblings.MonkeyBusiness.dto.MonkeyDTO;
import ramblings.MonkeyBusiness.dto.MonkeyResponseDTO;
import ramblings.MonkeyBusiness.model.Monkey;
import ramblings.MonkeyBusiness.model.Rank;
import ramblings.MonkeyBusiness.model.Status;

import static org.junit.jupiter.api.Assertions.*;

class MonkeyMapperTest {
    private MonkeyMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new MonkeyMapper();
    }

    @Test
    public void shouldThrowNullPointerForNullMonkeyDTO() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toMonkey(null));
        assertEquals("The Monkey DTO is null.", exp.getMessage());
    }

    @Test
    public void shouldMapMonkeyDTOToMonkey() {
        // Given
        MonkeyDTO dto = new MonkeyDTO(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE,
                3
        );

        // When
        Monkey monkey = mapper.toMonkey(dto);

        // Then
        assertEquals(dto.name(), monkey.getName());
        assertEquals(dto.rank(), monkey.getRank());
        assertEquals(dto.status(), monkey.getStatus());
        assertNotNull(monkey.getTroop());
        assertEquals(dto.troopId(), monkey.getTroop().getId());
    }

    @Test
    public void shouldMapMonkeyToMonkeyResponseDTO() {
        // Given
        Monkey monkey = new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        );

        // When
        MonkeyResponseDTO response = mapper.toMonkeyResponseDTO(monkey);

        // then
        assertEquals(response.name(), monkey.getName());
        assertEquals(response.status(), monkey.getStatus());
        assertEquals(response.rank(), monkey.getRank());
    }
}