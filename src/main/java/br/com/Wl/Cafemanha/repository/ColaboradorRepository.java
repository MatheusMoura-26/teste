package br.com.Wl.Cafemanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Wl.Cafemanha.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, String>{

}
