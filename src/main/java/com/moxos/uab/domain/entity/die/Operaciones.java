package com.moxos.uab.domain.entity.die;

import com.moxos.uab.domain.entity.siiga.Entidad;
import lombok.Data;

import java.util.Date;

@Data
public class Operaciones extends Entidad {
    private int id_operaciones;
    private int id_resultados_gestion;
    private int id_programa;
    private int id_departamento;
    private String operaciones;
    private String titulo;
    private String descripcion;
    private String elaborador;
    private String resultado;
    private String vistas;
    private Date fec_publicacion;
    private int descargas;

    private String resultados_gestion;
    private String programa;
    private String departamento;
}
