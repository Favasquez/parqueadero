package co.com.ceiba.parqueadero.repositoryjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.parqueadero.entidades.FacturaEntidad;

public interface FacturaRepository extends JpaRepository<FacturaEntidad,Integer>{
	
	@Query("SELECT factura FROM Facturas factura WHERE factura.vehiculoEntidad.id = :idVehiculo AND factura.fechaSalida IS NULL")
	FacturaEntidad findBillToUpdate(@Param("idVehiculo") int idVehiculo);
	
	List<FacturaEntidad> findAll();
}
