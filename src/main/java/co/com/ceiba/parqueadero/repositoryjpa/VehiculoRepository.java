package co.com.ceiba.parqueadero.repositoryjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parqueadero.entidades.VehiculoEntidad;


public interface VehiculoRepository extends JpaRepository<VehiculoEntidad, Integer>{
	
	List<VehiculoEntidad> findAll();
	
	VehiculoEntidad findById(int id);
	
	VehiculoEntidad findByPlacaAllIgnoreCase(String placa);
	
	VehiculoEntidad findByPlacaAllIgnoreCaseAndParqueado(String placa, boolean esParqueado);
}
