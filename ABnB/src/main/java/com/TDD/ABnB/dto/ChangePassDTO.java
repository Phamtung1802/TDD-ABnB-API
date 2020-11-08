package com.TDD.ABnB.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassDTO {
    private String presentPass;
    private String newPass;
}
