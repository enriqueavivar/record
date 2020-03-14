package com.recordreport.entity;

import java.io.File;

public class Archivo {

    private String Path;
    private String Name;
    private String tipo;
    private File file;

    public Archivo(String Name, String path, String tipo, File file){
        this.Name = Name;
        this.Path = Path;
        this.tipo = tipo;
        this.file = file;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPath(String Path){
        this.Path = Path;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getName() {
        return this.Name;
    }

    public String getPath() {
        return this.Path;
    }

    public String getTipo() {
        return this.tipo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}