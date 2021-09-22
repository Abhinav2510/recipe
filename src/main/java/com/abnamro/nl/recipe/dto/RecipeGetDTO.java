package com.abnamro.nl.recipe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeGetDTO {

    private long recipeId;

    private String recipeName;
    private long prepTimeInMinutes;
    private long cookingTimeInMinutes;

    private boolean vegan;
    private boolean vegetarian;
    private long serves;

    private String cuisine;

    private String course;

    @Schema(required = true,example = "2016-01-01 10:11:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    private List<IngredientsDTO> ingredients;
    private List<InstructionDTO> instructions;
    private String createdBy;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InstructionDTO {

        private long instructionNumber;
        private String instructionText;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredientsDTO {
        @NotEmpty
        private String name;
        @NotEmpty
        private String unit;
        @Positive
        private double quantity;
    }

}
