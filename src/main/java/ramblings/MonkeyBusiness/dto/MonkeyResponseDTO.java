package ramblings.MonkeyBusiness.dto;

import ramblings.MonkeyBusiness.model.Rank;
import ramblings.MonkeyBusiness.model.Status;

public record MonkeyResponseDTO(
        String name,
        Rank rank,
        Status status
) {}
