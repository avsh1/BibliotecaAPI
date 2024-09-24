package org.esfe.repositorios;

import org.esfe.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {
}
