package org.serratec.curriculo.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculo.dto.CandidatoDto;
import org.serratec.curriculo.model.Escolaridade;
import org.serratec.curriculo.model.VagaDesejada;
import org.serratec.curriculo.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	
	@Autowired
	private CandidatoService service;
	
	@GetMapping
	public List<CandidatoDto> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> obterPorId(@PathVariable Long id) {
		Optional<CandidatoDto> dto = service.obterporId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
//	@GetMapping("/vaga/{vaga}")
//	public List<CandidatoDto> obterPorVaga(@PathVariable VagaDesejada vagaDesejada) {
//		return service.obterporVaga(vagaDesejada);
//	}
//	
//	@GetMapping("/escolaridade/{escolaridade}")
//	public List<CandidatoDto> obterPorCliente(@PathVariable Escolaridade escolaridade) {
//		return service.obterPorEscolaridade(escolaridade);
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidato(@RequestBody CandidatoDto dto) {
		return service.salvarCandidato(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCandidato(@PathVariable Long id){
		if(!service.excluirCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> modificarCandidato(
			@PathVariable Long id, @RequestBody CandidatoDto dto){
		Optional<CandidatoDto> candidatoAlterado = service.modificarCandidato(id, dto);
		if (!candidatoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatoAlterado.get());
	}
}
