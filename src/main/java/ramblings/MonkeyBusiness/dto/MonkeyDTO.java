package ramblings.MonkeyBusiness.dto;

import jakarta.validation.constraints.NotEmpty;
import ramblings.MonkeyBusiness.model.Rank;
import ramblings.MonkeyBusiness.model.Status;

public record MonkeyDTO(
        @NotEmpty(message = "name should not be empty")
        String name,
        Rank rank,
        Status status,
        Integer troopId
) {}
