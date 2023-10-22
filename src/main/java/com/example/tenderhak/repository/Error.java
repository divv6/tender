package com.example.tenderhak.repository;

import com.example.tenderhak.model.ClassEnum;
import com.example.tenderhak.model.TypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String errorKey;
    private LocalDateTime date;
    private String error;
    @ManyToOne
    private Type errorType;

}
