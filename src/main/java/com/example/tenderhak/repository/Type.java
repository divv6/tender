package com.example.tenderhak.repository;

import com.example.tenderhak.model.ClassEnum;
import com.example.tenderhak.model.ErrorStatus;
import com.example.tenderhak.model.TypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private ClassEnum classType;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    private String key;
    private String title;
    private String description;
    private String solution;
    @Enumerated(EnumType.STRING)
    private ErrorStatus status;

}
