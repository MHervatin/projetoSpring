package com.msilva.cursoSpring.security;

import com.msilva.cursoSpring.domain.enums.PerfilCliente;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Clsse responsável por prover o usuário spring de segurança.
 *
 * @author Mateus
 */
public class UsuarioSpringSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String senha;
    private Collection<GrantedAuthority> authorities;

    public UsuarioSpringSecurity() {
    }

    public UsuarioSpringSecurity(Long id, String email, String senha,
            Set<PerfilCliente> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                .collect(Collectors.toList());
    }

    public Long getID() {
        return id;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPassword() {
        return senha;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
