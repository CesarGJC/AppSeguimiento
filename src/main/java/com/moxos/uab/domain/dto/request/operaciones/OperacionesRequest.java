package com.moxos.uab.domain.dto.request.operaciones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class OperacionesRequest {
    private int id_operaciones;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_resultados_gestion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_programa;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int id_departamento;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String operaciones;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String titulo;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String descripcion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String elaborador;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String resultado;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private String vistas;
    @NotNull(message = "Inserte Mensaje de Validacion")
    @NotBlank(message = "Inserte Mensaje de Validacion")
    private Date fec_publicacion;
    @NotNull(message = "Inserte Mensaje de Validacion")
    private int descargas;
}
