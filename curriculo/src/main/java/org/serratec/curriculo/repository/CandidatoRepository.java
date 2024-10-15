package org.serratec.curriculo.repository;

import java.util.List;

import org.serratec.curriculo.model.Candidato;
import org.serratec.curriculo.model.Escolaridade;
import org.serratec.curriculo.model.VagaDesejada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
	
//	List<Candidato> findByVagaIgnoreCase(VagaDesejada vagaDesejada);
//	List<Candidato> findByEscolaridadeIgnoreCase(Escolaridade escolaridade);
}
