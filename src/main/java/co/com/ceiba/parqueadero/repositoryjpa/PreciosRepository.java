package co.com.ceiba.parqueadero.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parqueadero.entidades.PreciosEntidad;

public interface PreciosRepository extends JpaRepository<PreciosEntidad, Integer>{

	PreciosEntidad findById(int id);
	
}
