package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepository {

    Usuario save(Usuario usuario);

    Usuario findByCpf(String cpf);

    Page<Usuario> findAllByAtivoTrue(Pageable pageable);

    void delete(String cpf);

    void deactivate(String cpf);
}
