package com.vinicius.rinhabackend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vinicius.rinhabackend.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID>{

	@Query(value = "SELECT p.id, p.apelido, p.nome, TO_CHAR(p.nascimento, 'YYYY-MM-DD') as nascimento, p.stack " +
            "FROM pessoas p " +
            "WHERE p.searchable ILIKE %:term% " +
            "LIMIT 50", nativeQuery = true)
	List<Pessoa> findByTerm(@Param("term") String term);
	

}
