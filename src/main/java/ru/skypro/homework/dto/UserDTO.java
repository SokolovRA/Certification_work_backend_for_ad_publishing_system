package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

        private Long id;

        private String firstName;

        private String lastName;

        private String email;

        private String phone;

        private String image;
}