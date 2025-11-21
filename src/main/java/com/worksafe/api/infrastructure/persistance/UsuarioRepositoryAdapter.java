package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Credenciais;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.UsuarioRepository;
import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.entity.JpaRoleEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaCredenciaisRepository;
import com.worksafe.api.infrastructure.repository.JpaRoleRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.CredenciaisMapper;
import com.worksafe.api.interfaces.mapper.UsuarioMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final JpaCredenciaisRepository jpaCredenciaisRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final PasswordEncoder encoder;
    private final Logger logger;

    public UsuarioRepositoryAdapter(JpaUsuarioRepository jpaUsuarioRepository, JpaCredenciaisRepository jpaCredenciaisRepository, JpaRoleRepository jpaRoleRepository, PasswordEncoder encoder, Logger logger) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.jpaCredenciaisRepository = jpaCredenciaisRepository;
        this.jpaRoleRepository = jpaRoleRepository;
        this.encoder = encoder;
        this.logger = logger;
    }

    @Override
    public Usuario save(Usuario usuario) {
        logger.info("Salvando usuário com CPF: " + usuario.getCpf());
        JpaUsuarioEntity jpaUsuarioEntity = UsuarioMapper.toJpa(usuario);

        try{
            Credenciais credenciais = new Credenciais(
                    usuario.getCredenciais().getUsername(),
                    usuario.getCredenciais().getPassword(),
                    usuario.getNome() + " " + usuario.getSobrenome()
            );

            JpaRoleEntity roleEntity = jpaRoleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Role padrão ROLE_USER não encontrada"));

            JpaCredenciaisEntity credenciaisEntity = CredenciaisMapper.toEntity(credenciais, roleEntity);

            String senhaCriptografada = encoder.encode(credenciaisEntity.getPassword());
            credenciaisEntity.setPassword(senhaCriptografada);
            JpaCredenciaisEntity savedCredenciais = jpaCredenciaisRepository.save(credenciaisEntity);

            jpaUsuarioEntity.setCredenciais(savedCredenciais);

            JpaUsuarioEntity savedEntity = jpaUsuarioRepository.save(jpaUsuarioEntity);

            logger.info("Usuario salvo com sucesso: " + usuario.getCpf());
            return UsuarioMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e){
            logger.error("Erro de acesso a dados: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao salvar usuário.", e);

        } catch (RuntimeException e) {
            logger.error("Erro inesperado ao salvar usuário: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro inesperado ao salvar usuário.", e);
        }
    }

    @Override
    public Usuario findByCpf(String cpf) {
        logger.info("Buscando usuario por cpf: " + cpf);
        try {
            JpaUsuarioEntity entity = jpaUsuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do id: " + cpf + " não encontrado"));

            logger.info("Usuario encontrado: " + entity.getId() + " " + entity.getSobrenome());
            return UsuarioMapper.entityToDomain(entity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar usuario por cpf: " + e.getMessage());
        }
    }

    @Override
    public Page<Usuario> findAllByAtivoTrue(Pageable pageable) {
        logger.info("Buscando todos os usuarios ativos");
        try{
            Page<JpaUsuarioEntity> usuarios = jpaUsuarioRepository.findAllByAtivoTrue();
            logger.info("Usuarios ativos encontrados: " + usuarios.getTotalElements());
            return usuarios.map(UsuarioMapper::entityToDomain);
        } catch (DataAccessException e) {
            throw new InfraestruturaException("Erro ao buscar usuarios ativos " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String cpf) {
        logger.info("Deletando usuario com cpf: " + cpf);
        try{
            JpaUsuarioEntity entity = jpaUsuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do cpf: " + cpf + " não encontrado"));
            jpaUsuarioRepository.delete(entity);
            logger.info("Usuario deletado com sucesso: " + cpf);
        } catch (DataAccessException e){
            logger.error("Erro ao deletar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao deletar usuario com cpf: " + cpf, e);
        }
    }

    @Override
    public void deactivate(String cpf) {
        logger.info("Desativando usuario com cpf: " + cpf);
        try {
            JpaUsuarioEntity entity = jpaUsuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do cpf: " + cpf + " não encontrado"));
            entity.setAtivo(false);
            jpaUsuarioRepository.save(entity);
            logger.info("Usuario desativado com sucesso: " + cpf);
        } catch (DataAccessException e){
            logger.error("Erro ao desativar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao desativar usuario com cpf: " + cpf, e);
        }
    }

    @Override
    public Usuario findById(Long id) {
        logger.info("Buscando usuario por id: " + id);
        try {
            JpaUsuarioEntity entity = jpaUsuarioRepository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do id: " + id + " não encontrado"));
            logger.info("Usuario encontrado: " + entity.getId() + " " + entity.getSobrenome());
            return UsuarioMapper.entityToDomain(entity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar usuario por id: " + e.getMessage());
        }
    }
}
