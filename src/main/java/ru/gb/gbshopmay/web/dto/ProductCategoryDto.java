//package ru.gb.gbshopmay.web.dto;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
//import ru.gb.gbshopmay.entity.enums.Status;
//
//import javax.validation.constraints.*;
//import java.math.BigDecimal;
//
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class ProductCategoryDto {
//    private Long id;
//    @NotBlank
//    private String title;
//    @NotNull
//    @DecimalMin(value = "0.0", inclusive = false)
//    @Digits(integer = 6, fraction = 2)
//    private BigDecimal cost;
//    @NotNull
//    private Status status;
//    @JsonProperty(value = "category")
//    private CategoryDto categoryDto;
//}