package com.moxos.uab.persistence.die;

import com.moxos.uab.domain.dto.request.formulario.FormularioFilterRequest;
import com.moxos.uab.domain.dto.request.general.FilterRequest;
import com.moxos.uab.domain.entity.die.Operaciones;
import com.moxos.uab.persistence.die.provider.FormularioSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;
@Mapper
public interface OperacionesDao {

    @SelectProvider(type = FormularioSqlProvider.class, method = "getOperaciones")
    List<Operaciones> getOperaciones(@Param("filtro") FilterRequest<FormularioFilterRequest> filtro);

    @SelectProvider(type = FormularioSqlProvider.class, method = "getCantidadOperaciones")
    Integer getCantidadOperaciones(@Param("filtro") FilterRequest<FormularioFilterRequest> filtro);

    Integer saveOperaciones(Operaciones operaciones) throws DataAccessException;

    void deleteOperaciones(Operaciones operaciones) throws DataAccessException;

    List<Operaciones> getAllOperaciones() throws DataAccessException;

    Operaciones getByid(int id_operaciones) throws DataAccessException;

    List<Operaciones> getOperacionesByid(Integer id) throws DataAccessException;
}
