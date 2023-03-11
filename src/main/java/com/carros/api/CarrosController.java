package com.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired
	private CarroService service;

	@GetMapping
	public Iterable<Carro> get() {
		return service.getCarros();
	}

	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return service.getCarroById(id);
	}

	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarroByTipo(tipo);
	}

	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		return "Carro salvo com sucesso: " + c.getId();
	}

	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);

		return "Carro id: " + c.getId() + " atualizado com sucesso";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "Carro " + id + " deletado com sucesso";
	}
}
