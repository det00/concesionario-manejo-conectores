package models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pasajero {
    private long id;
    private String nombre;
    private int edad;
    private double peso;
    Integer coche;

    public Pasajero(String nombre, int edad, int peso, Integer coche) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.coche = coche;
    }
}
