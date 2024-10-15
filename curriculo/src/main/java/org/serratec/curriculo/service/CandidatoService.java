package org.serratec.curriculo.service;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculo.dto.CandidatoDto;
import org.serratec.curriculo.model.Candidato;
import org.serratec.curriculo.model.Escolaridade;
import org.serratec.curriculo.model.VagaDesejada;
import org.serratec.curriculo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	
	@Autowired
	private CandidatoRepository repository;
	
	public List<CandidatoDto> obterTodos(){
		return repository.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public Optional<CandidatoDto> obterporId(Long id){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		
		return Optional.of(CandidatoDto.toDto(repository.findById(id).get()));
	}
	
	public CandidatoDto salvarCandidato(CandidatoDto dto) {
		return CandidatoDto.toDto(repository.save(dto.toEntity()));
	}
	
	public boolean excluirCandidato(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
	
	public Optional<CandidatoDto> modificarCandidato(Long id, CandidatoDto dto){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		
		Candidato candidatoEntity = dto.toEntity();
		candidatoEntity.setId(id);
		repository.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}
	
//	public List<CandidatoDto> obterporVaga(VagaDesejada vagaDesejada){
//		List<Candidato> candidatos = repository.findByVagaIgnoreCase(vagaDesejada);
//		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
//	}
//	
//	public List<CandidatoDto> obterPorEscolaridade(Escolaridade escolaridade) {
//		List<Candidato> candidatos = repository.findByEscolaridadeIgnoreCase(escolaridade);
//		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
//	}
}
