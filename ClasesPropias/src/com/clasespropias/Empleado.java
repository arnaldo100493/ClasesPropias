/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

/**
 *
 * @author FABAME
 */
public class Empleado {

    private String identificacion;
    private String numeroEmpleado;
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;

    public Empleado() {
        this.identificacion = "";
        this.numeroEmpleado = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.salario = 0;
    }

    public Empleado(String identifiacion, String numeroEmpleado, String nombre, String apellido, int edad, double salario) {
        this.identificacion = identifiacion;
        this.numeroEmpleado = "";
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salario = salario;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "identificacion=" + identificacion + ", numeroEmpleado=" + numeroEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", salario=" + salario + '}';
    }

}
