package com.example.tenderhak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {

    private RoleEnum role;
    private String name;
    private String surname;
}
