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
    private int plazasDisponibles;

    public Coche(Long id, String matricula, String marca, String modelo, String color) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
}

