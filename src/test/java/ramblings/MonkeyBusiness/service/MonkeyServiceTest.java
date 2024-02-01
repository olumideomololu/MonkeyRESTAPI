package ramblings.MonkeyBusiness.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ramblings.MonkeyBusiness.dto.MonkeyDTO;
import ramblings.MonkeyBusiness.dto.MonkeyResponseDTO;
import ramblings.MonkeyBusiness.model.Monkey;
import ramblings.MonkeyBusiness.model.Rank;
import ramblings.MonkeyBusiness.model.Status;
import ramblings.MonkeyBusiness.repository.MonkeyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MonkeyServiceTest {
    // service to test
    @InjectMocks
    private MonkeyService monkeyService;

    // dependencies
    @Mock
    private MonkeyRepository repository;
    @Mock
    private MonkeyMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveStudent() {
        // Given
        Monkey monkey = new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        );
        Monkey savedMonkey = new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        );
        MonkeyDTO dto = new MonkeyDTO(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE,
                3
        );

        // Mock the calls
        when(mapper.toMonkey(dto)).thenReturn(monkey);
        when(repository.save(monkey)).thenReturn(savedMonkey);
        when(mapper.toMonkeyResponseDTO(savedMonkey))
                .thenReturn(new MonkeyResponseDTO(
                        "Donkey Kong",
                        Rank.BETA,
                        Status.ALIVE
                ));

        // When
        MonkeyResponseDTO responseDTO = monkeyService.saveMonkey(dto);

        // Then
        assertEquals(dto.name(), responseDTO.name());
        assertEquals(dto.rank(), responseDTO.rank());
        assertEquals(dto.status(), responseDTO.status());

        verify(mapper, times(1)).toMonkey(dto);
        verify(repository, times(1)).save(monkey);
        verify(mapper, times(1)).toMonkeyResponseDTO(savedMonkey);
    }

    @Test
    public void shouldReturnAllMonkeys() {
        // Given
        List<Monkey> monkeys = new ArrayList<>();
        monkeys.add(new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        ));

        // Mock the calls
        when(repository.findAll()).thenReturn(monkeys);
        when(mapper.toMonkeyResponseDTO(any(Monkey.class)))
                .thenReturn(new MonkeyResponseDTO(
                        "Donkey Kong",
                        Rank.BETA,
                        Status.ALIVE
                ));

        // When
        List<MonkeyResponseDTO> responseDTOs = monkeyService.findAll();

        // Then
        assertEquals(monkeys.size(), responseDTOs.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldReturnStudentByID() {
        // Given
        Integer monkeyId = 1;
        Monkey monkey = new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        );

        // Mock the calls
        when(repository.findById(monkeyId)).thenReturn(Optional.of(monkey));
        when(mapper.toMonkeyResponseDTO(any(Monkey.class)))
                .thenReturn(new MonkeyResponseDTO(
                        "Donkey Kong",
                        Rank.BETA,
                        Status.ALIVE
                ));
        // When
        MonkeyResponseDTO dto = monkeyService.findMonkeyById(monkeyId);

        // Then
        assertEquals(dto.name(), monkey.getName());
        assertEquals(dto.rank(), monkey.getRank());
        assertEquals(dto.status(), monkey.getStatus());

        verify(repository, times(1)).findById(monkeyId);
    }

    @Test
    public void shouldFindStudentByName(){
        // Given
        String monkeyName = "Donkey Kong";
        List<Monkey> monkeys = new ArrayList<Monkey>();
        monkeys.add(new Monkey(
                "Donkey Kong",
                Rank.BETA,
                Status.ALIVE
        ));

        // Mock the calls
        when(repository.findAllByNameContaining(monkeyName))
                .thenReturn(monkeys);
        when(mapper.toMonkeyResponseDTO(any(Monkey.class)))
                .thenReturn(new MonkeyResponseDTO(
                        "Donkey Kong",
                        Rank.BETA,
                        Status.ALIVE
                ));

        // When
        var responseDTOs = monkeyService.findMonkeyByName(monkeyName);

        // Then
        assertEquals(monkeys.size(), responseDTOs.size());
        verify(repository, times(1))
                .findAllByNameContaining(monkeyName);
    }

}