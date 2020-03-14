package com.recordreport.entity;

public class CampoFormulario {

    private String nombre;
    private String[] valores;

    public CampoFormulario(String nombre, String[] valores) {
        this.nombre = nombre;
        this.valores = valores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getValores() {
        return valores;
    }

    public void setValores(String[] valores) {
        this.valores = valores;
    }
}
