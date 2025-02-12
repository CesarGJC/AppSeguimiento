package com.moxos.uab.business.facade;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.OperacionesResponse;

public interface IActividadesProgramadasFacade {

    IndexViewModelFilter<OperacionesResponse, Integer> getOperaciones(ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> busqueda);

    OperacionesRequest getOperacion(int idOperaciones);

    Response<OperacionesResponse> saveOperaciones(OperacionesRequest operacionesRequest);

    GeneralResponse deleteOperaciones(OperacionesRequest model);
}
