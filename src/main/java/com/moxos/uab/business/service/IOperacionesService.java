package com.moxos.uab.business.service;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.OperacionesResponse;

import java.util.List;

public interface IOperacionesService {
    Response<List<OperacionesResponse>> getOperaciones(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> getCantidadByTipo(FilterRequest<FormularioFilterRequest> buscar);

    Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest);

    GeneralResponse deleteOperaciones(OperacionesRequest operacionesRequest);

    Response<OperacionesResponse> getByid(int id_operaciones);


//    Response<List<OperacionesResponse>> getOperacion(int id_operaciones);
}
