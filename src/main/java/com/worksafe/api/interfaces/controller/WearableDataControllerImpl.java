package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.usuario.BuscarUsuarioPorIdUseCase;
import com.worksafe.api.application.usecase.wearable.data.*;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.interfaces.dto.input.WearableRequest;
import com.worksafe.api.interfaces.dto.output.WearableResponse;
import com.worksafe.api.interfaces.mapper.WearableMapper;

import java.util.List;

public final class WearableDataControllerImpl implements WearableDataController {

    private final CreateWearableDataUseCase createWearableDataUseCase;
    private final BuscarWearableDataByIdUseCase buscarWearableDataByIdUseCase;
    private final ListarWearableDataUseCase listarWearableDataUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public WearableDataControllerImpl(CreateWearableDataUseCase createWearableDataUseCase, BuscarWearableDataByIdUseCase buscarWearableDataByIdUseCase, ListarWearableDataUseCase listarWearableDataUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.createWearableDataUseCase = createWearableDataUseCase;
        this.buscarWearableDataByIdUseCase = buscarWearableDataByIdUseCase;
        this.listarWearableDataUseCase = listarWearableDataUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @Override
    public WearableResponse create(WearableRequest request) {
        WearableData wearableData = WearableMapper.toModel(request);
        WearableData createdWearableData = createWearableDataUseCase.execute(wearableData);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(request.usuarioId());
        return WearableMapper.toResponse(createdWearableData, usuario.getNome());
    }

    @Override
    public WearableResponse buscarPorId(Long id) {
        WearableData wearableData = buscarWearableDataByIdUseCase.execute(id);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(wearableData.getUsuarioId());
        return WearableMapper.toResponse(wearableData, usuario.getNome());
    }

    @Override
    public List<WearableResponse> listarDados() {
        List<WearableData> wearableDataList = listarWearableDataUseCase.execute();
        return wearableDataList.stream()
                .map(x -> {
                    Usuario usuario = buscarUsuarioPorIdUseCase.execute(x.getUsuarioId());
                    return WearableMapper.toResponse(x, usuario.getNome());
                        })
                .toList();
    }
}
