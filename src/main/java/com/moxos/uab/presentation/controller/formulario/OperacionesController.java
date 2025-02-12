package com.moxos.uab.presentation.controller.formulario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxos.uab.business.facade.IActividadesProgramadasFacade;
import com.moxos.uab.common.util.ReflectionUtils;
import com.moxos.uab.common.util.filter.FiltersUtils;
import com.moxos.uab.common.util.web.CookieFilter;
import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.*;
import com.moxos.uab.domain.entity.siiga.Clientes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/operaciones")
public class OperacionesController {
    private CookieFilter<FilterRequest<FormularioFilterRequest>> cookieFilter;
    private final IActividadesProgramadasFacade actividadesFacade;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OperacionesController(IActividadesProgramadasFacade actividadesFacade, HttpServletRequest request, HttpServletResponse response) {
        this.actividadesFacade = actividadesFacade;
        this.request = request;
        this.response = response;
    }


    private Clientes getUsuario() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (Clientes) attr.getRequest().getSession().getAttribute("__sess_cliente");
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<FormularioFilterRequest> model, Model modelo) {
        return "Formulario/Operaciones/Index" ;
    }

    @GetMapping("/listar")
    public String listar(@ModelAttribute("model") ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, @ModelAttribute("filter") FilterParamsRequest filter, @ModelAttribute("cookie") boolean cookie, @CookieValue(value = "filter", required = false) String value, Model modelo) throws JsonProcessingException {
        var paginacion = actividadesFacade.getOperaciones(setCookieFilter(model, filter, cookie, value));
        double cantidadpaginas = Math.ceil((double) paginacion.getTotaldeRegistros() / paginacion.getRegistrosporPagina());
        modelo.addAttribute("cantidadpaginas", cantidadpaginas);
        modelo.addAttribute("model", paginacion);
        return "Formulario/Operaciones/_Listar";
    }
    private ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>> setCookieFilter(ParametrosPaginacionBusquedaRequest<FilterParamsRequest> model, FilterParamsRequest filter, boolean cookie, String value) throws JsonProcessingException {
        var requestFilter = createCoockie(cookie, value, filter);
        var parametros = new ParametrosPaginacionBusquedaRequest<FilterRequest<FormularioFilterRequest>>();
        parametros.setBuscar(model.getBuscar());
        parametros.setMostrar(model.getMostrar());
        parametros.setPagina(model.getPagina());
        parametros.setOption(requestFilter);
        return parametros;
    }

    private FilterRequest<FormularioFilterRequest> createCoockie(boolean cookie, String value, FilterParamsRequest filter) throws JsonProcessingException {
        if (cookieFilter == null) cookieFilter = new CookieFilter<>("filter", request, response);
        if (cookie) {
            cookieFilter.reset();
            value = null;
        }
        FilterRequest<FormularioFilterRequest> requestFilter;
        if (value == null) {
            cookieFilter.init();
            requestFilter = new FilterRequest<>();
        } else
            requestFilter = objectMapper.readValue(value, new TypeReference<FilterRequest<FormularioFilterRequest>>() {
            });
        if (requestFilter.getFilter() == null) requestFilter.setFilter(new FormularioFilterRequest());
        ReflectionUtils.toAssinateValue(requestFilter.getFilter(), filter.getOption(), filter.getValue());
        requestFilter.setParams(FiltersUtils.setValue(requestFilter.getParams(), filter));
        cookieFilter.setData(requestFilter);
        return requestFilter;
    }

}
