package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Cliente;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Cliente}.
 *
 * @author Mateus
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca um Cliente por um e-mail específico na base de dados.
     *
     * @param email O e-mail buscado.
     *
     * @return O cliente que tem o e-mail buscado.
     */
    @Transactional
    public Cliente findByEmail(String email);
}
