package org.esfe.repositorios;

import org.esfe.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Integer> {
}
