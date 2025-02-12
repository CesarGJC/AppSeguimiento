package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IOperacionesService;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.dto.request.operaciones.OperacionesRequest;
import com.moxos.uab.domain.dto.response.GeneralResponse;
import com.moxos.uab.domain.dto.response.Response;
import com.moxos.uab.domain.dto.response.operaciones.OperacionesResponse;
import com.moxos.uab.domain.entity.die.Operaciones;
import com.moxos.uab.persistence.die.OperacionesDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacionesServiceImpl implements IOperacionesService {
    private final ModelMapper modelMapper;
    private final OperacionesDao operacionesDao;

    public OperacionesServiceImpl(ModelMapper modelMapper, OperacionesDao operacionesDao) {
        this.modelMapper = modelMapper;
        this.operacionesDao = operacionesDao;
    }


    @Override
    public Response<List<OperacionesResponse>> getOperaciones(FilterRequest<FormularioFilterRequest> buscar) {
        try {
            List<OperacionesResponse> operaciones = operacionesDao.getOperaciones(buscar).stream().map(p -> modelMapper.map(p, OperacionesResponse.class)).toList();
            return new Response<>(true, "", operaciones);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> getCantidadByTipo(FilterRequest<FormularioFilterRequest> buscar) {
        try {
            Integer cantidad = operacionesDao.getCantidadOperaciones(buscar);
            return new Response<>(true, "", cantidad);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

    @Override
    public Response<Integer> saveOperaciones(OperacionesRequest operacionesRequest) {
        try {
            Integer result = operacionesDao.saveOperaciones(modelMapper.map(operacionesRequest, Operaciones.class));
            return new Response<>(true, "", result);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), -1);
        }
    }

    @Override
    public GeneralResponse deleteOperaciones(OperacionesRequest operacionesRequest) {
        try {
            operacionesDao.deleteOperaciones(modelMapper.map(operacionesRequest, Operaciones.class));
            return new GeneralResponse(true, "");
        } catch (Exception e) {
            return new GeneralResponse(false, e.getMessage());
        }
    }

    @Override
    public Response<OperacionesResponse> getByid(int id_evaluacion_desempeno) {
        try {
            OperacionesResponse operacionesResponse = modelMapper.map(operacionesDao.getByid(id_evaluacion_desempeno), OperacionesResponse.class);
            return new Response<>(true, "", operacionesResponse);
        } catch (Exception e) {
            return new Response<>(false, e.getMessage(), null);
        }
    }

//    @Override
//    public Response<List<OperacionesResponse>> getOperacion(int id_operaciones) {
//        try {
//            List<OperacionesResponse> listViews = new ArrayList<>();
//            for (var item : operacionesDao.getOperacionesByid(id_operaciones))
//                listViews.add(new OperacionesResponse(item.getId_operaciones(), item.getOperaciones()));
//            return new Response<>(true, "", listViews);
//        } catch (Exception e) {
//            return new Response<>(false, e.getMessage(), null);
//        }
//    }
}
