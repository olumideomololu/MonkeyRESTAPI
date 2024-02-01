package ramblings.MonkeyBusiness.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ramblings.MonkeyBusiness.dto.MonkeyDTO;
import ramblings.MonkeyBusiness.dto.MonkeyResponseDTO;
import ramblings.MonkeyBusiness.service.MonkeyService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MonkeyController {
    private final MonkeyService monkeyService;

    public MonkeyController(MonkeyService monkeyService) {
        this.monkeyService = monkeyService;
    }

    @GetMapping("/monkeys")
    public List<MonkeyResponseDTO> findAll() {
        return monkeyService.findAll();
    }

    @GetMapping("/monkeys/{id}")
    public MonkeyResponseDTO findMonkeyById(@PathVariable("id") Integer id) {
        return monkeyService.findMonkeyById(id);
    }

    @GetMapping("/monkeys/search/{name}")
    public List<MonkeyResponseDTO> findMonkeyByName(@PathVariable("name") String name) {
        return monkeyService.findMonkeyByName(name);
    }

    @PostMapping("/monkeys")
    public MonkeyResponseDTO saveMonkey(@Valid @RequestBody MonkeyDTO dto) {
        return monkeyService.saveMonkey(dto);
    }

    @DeleteMapping("/monkeys/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id){
        monkeyService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

/*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exp
    ) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }
*/

}
