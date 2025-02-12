package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.business.service.IOperacionesService;
import com.moxos.uab.common.util.RequestUtils;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.general.IndexViewModelFilter;
import com.moxos.uab.domain.dto.request.general.ParametrosPaginacionBusquedaRequest;
import com.moxos.uab.domain.dto.request.general.SelectListItemDto;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.OperacionesResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActividadesProgramadasFacadeImpl implements IActividadesProgramadasFacade {
    private final IOperacionesService operacionesService;
    private final ModelMapper modelMapper;

    public ActividadesProgramadasFacadeImpl(IOperacionesService operacionesService, ModelMapper modelMapper) {
        this.operacionesService = operacionesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public OperacionesRequest getOperacion(int idOperaciones) {
        var response= operacionesService.getByid(idOperaciones);
        return modelMapper.map(response.getResult(), OperacionesRequest.class);
    }

    @Override
    public Response<OperacionesResponse> saveOperaciones(OperacionesRequest operacionesRequest) {
        Response<Integer> result=operacionesService.saveOperaciones(operacionesRequest);
        return operacionesService.getByid(result.getResult());
    }

    @Override
    public GeneralResponse deleteOperaciones(OperacionesRequest model) {
        return operacionesService.deleteOperaciones(model);
    }

    @Override
    public IndexViewModelFilter<OperacionesResponse, Integer> getOperaciones(ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> busqueda) {
//        IndexViewModelFilter<OperacionesResponse, Integer> filtro = new IndexViewModelFilter<>();
//
//        //Lista para mostrar el número de elementos
//        List<SelectListItemDto> mostrarElementos = RequestUtils.getCantidadDeElementos();
//
//        //Cantidad a mostrar por página
//        int cantidadRegistroPagina = busqueda.getMostrar();
//
//        //Mostrar la pagina actual
//        int pagina = (busqueda.getPagina() - 1) * cantidadRegistroPagina;
//
//        //Parametro de busqueda en elementos
//        FilterRequest<FormularioFilterRequest> opcion = busqueda.getOption();
//        opcion.getFilter().setPagina(cantidadRegistroPagina);
//        opcion.getFilter().setNro_pagina(pagina);
//        //Listo elemento a mostrar
//        Response<List<OperacionesResponse>> operaciones = operacionesService.getOperaciones(opcion);
//        if (operaciones.isSuccess()) {
//            Response<Integer> totalRegistros = operacionesService.getCantidadByTipo(opcion);
//            filtro.setTotaldeRegistros(totalRegistros.getResult());
//        } else {
//            filtro.setTotaldeRegistros(0);
//        }
//        filtro.setLista(operaciones.getResult());
//        filtro.setPaginaActual(busqueda.getPagina());
//        filtro.setRegistrosporPagina(cantidadRegistroPagina);
//        filtro.setMostrarElementos(mostrarElementos);
//        filtro.setMostrar(cantidadRegistroPagina);
//        return filtro;
        return null;
    }
}
