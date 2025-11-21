package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Credenciais;
import com.worksafe.api.domain.entity.Endereco;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.interfaces.dto.input.UsuarioRequest;
import com.worksafe.api.interfaces.dto.output.EnderecoResponse;
import com.worksafe.api.interfaces.dto.output.UsuarioDetailsResponse;
import com.worksafe.api.interfaces.dto.output.UsuarioResponse;

public final class UsuarioMapper {

    private UsuarioMapper() {
        super();
    }

    public static Usuario toModel(UsuarioRequest request) {
        Credenciais credenciais = new Credenciais(
                request.credenciais().username(),
                request.credenciais().password(),
                request.nome() + " " + request.sobrenome()
        );

        Endereco endereco = new Endereco(
                request.endereco().logradouro(),
                request.endereco().bairro(),
                request.endereco().cep(),
                request.endereco().numero(),
                request.endereco().complemento(),
                request.endereco().cidade(),
                request.endereco().uf()
        );

        return new Usuario(
                request.nome(),
                request.sobrenome(),
                request.cpf(),
                request.sexo(),
                request.email(),
                request.telefone(),
                credenciais,
                request.cargo(),
                request.departamento(),
                request.dataNascimento(),
                endereco
        );
    }


    public static UsuarioResponse toResponse(Usuario createdUsuario) {
        EnderecoResponse endereco = new EnderecoResponse(
                createdUsuario.getEndereco().getLogradouro(),
                createdUsuario.getEndereco().getBairro(),
                createdUsuario.getEndereco().getCep(),
                createdUsuario.getEndereco().getNumero(),
                createdUsuario.getEndereco().getComplemento(),
                createdUsuario.getEndereco().getCidade(),
                createdUsuario.getEndereco().getUf()
        );

        return new UsuarioResponse(
                createdUsuario.getNome(),
                createdUsuario.getSobrenome(),
                createdUsuario.getCpf(),
                createdUsuario.getSexo(),
                createdUsuario.getEmail(),
                createdUsuario.getTelefone(),
                createdUsuario.getCargo(),
                createdUsuario.getDepartamento(),
                createdUsuario.getDataNascimento(),
                createdUsuario.getCreatedAt(),
                endereco
        );
    }

    public static UsuarioDetailsResponse toDetailsResponse(Usuario usuario) {
        return new UsuarioDetailsResponse(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getCargo(),
                usuario.getDepartamento(),
                usuario.getDataNascimento(),
                usuario.getCreatedAt()
        );
    }

    public static JpaUsuarioEntity toJpa(Usuario usuario) {
        Endereco endereco = toEnderecoModel(
                usuario.getEndereco().getLogradouro(),
                usuario.getEndereco().getBairro(),
                usuario.getEndereco().getCep(),
                usuario.getEndereco().getNumero(),
                usuario.getEndereco().getComplemento(),
                usuario.getEndereco().getCidade(),
                usuario.getEndereco().getUf()
        );

        return new JpaUsuarioEntity(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getSexo(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCargo(),
                usuario.getDepartamento(),
                usuario.getDataNascimento(),
                usuario.getCreatedAt(),
                usuario.getAtivo(),
                endereco
        );
    }

    public static Endereco toEnderecoModel(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String uf) {
        return new Endereco(
                logradouro, bairro, cep, numero, complemento, cidade, uf
        );
    }

    public static Usuario entityToDomain(JpaUsuarioEntity savedEntity) {
        return new Usuario(
                savedEntity.getId(),
                savedEntity.getNome(),
                savedEntity.getSobrenome(),
                savedEntity.getCpf(),
                savedEntity.getSexo(),
                savedEntity.getEmail(),
                savedEntity.getTelefone(),
                savedEntity.getCargo(),
                savedEntity.getDepartamento(),
                savedEntity.getDataNascimento(),
                savedEntity.getCreatedAt(),
                savedEntity.getAtivo(),
                savedEntity.getEndereco()
        );
    }
}
