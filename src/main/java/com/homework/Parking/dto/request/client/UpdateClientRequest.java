package com.homework.Parking.dto.request.client;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateClientRequest {
    @NotBlank(message = "Поле имя не может быть пустым.")
    @Pattern(regexp = "^[а-яА-ЯёЁ]+\\s[а-яА-ЯёЁ]+$", message = "Введите фамилию и имя через пробел (только кириллические буквы).")
    private String name;

    @NotNull(message = "Поле возраст не может быть пустым.")
    @Min (value = 18, message = "Возраст должен быть больше 18 лет.")
    private Integer age;

    @NotBlank(message = "Поле номер телефона не может быть пустым.")
    @Pattern(regexp = "^\\+?7\\d{10}$" , message = "Введите корректный номер телефона в виде '+79876543210'.")
    private String phoneNumber;

    private String email;
}
