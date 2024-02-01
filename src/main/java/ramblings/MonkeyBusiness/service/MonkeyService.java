package ramblings.MonkeyBusiness.service;

import org.springframework.stereotype.Service;
import ramblings.MonkeyBusiness.dto.MonkeyDTO;
import ramblings.MonkeyBusiness.dto.MonkeyResponseDTO;
import ramblings.MonkeyBusiness.repository.MonkeyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonkeyService {
    private final MonkeyRepository repository;
    private final MonkeyMapper monkeyMapper;

    public MonkeyService(MonkeyRepository repository, MonkeyMapper monkeyMapper) {
        this.repository = repository;
        this.monkeyMapper = monkeyMapper;
    }

    public MonkeyResponseDTO saveMonkey(MonkeyDTO dto){
        var monkey = monkeyMapper.toMonkey(dto);
        var savedMonkey = repository.save(monkey);
        return monkeyMapper.toMonkeyResponseDTO(savedMonkey);
    }

    public List<MonkeyResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(monkeyMapper::toMonkeyResponseDTO)
                .collect(Collectors.toList());
    }

    public MonkeyResponseDTO findMonkeyById(Integer id) {
        return repository.findById(id)
                .map(monkeyMapper::toMonkeyResponseDTO)
                .orElse(null);
    }

    public List<MonkeyResponseDTO> findMonkeyByName(String name) {
        return repository.findAllByNameContaining(name)
                .stream()
                .map(monkeyMapper::toMonkeyResponseDTO)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
