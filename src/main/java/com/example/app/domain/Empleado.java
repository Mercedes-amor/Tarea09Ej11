package com.example.app.domain;




import com.example.app.modelo.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@EqualsAndHashCode(of = "id")
@Entity //Declara que va a ser una entidad de la base de datos con nombre Empleado
public class Empleado {

    //@Id es obligatorio e indica que es la Primary Key de la entidad Empleado
    //Obligatoriamente es not null y único
    //Podríamos haberlo puesto a dni, matrícula, códigoCliente, etc
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera automáticamente el id autoincrementando
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Float salario;
    private Boolean enActivo;
    @NotNull
    private Genero genero;

    //Incorporación relación con Empleado creado
    @ManyToOne
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuario creador;
}
