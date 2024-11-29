package models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Coche{
    private Long id;
    private String matricula;
    private String marca, modelo, color;
}

