package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Aporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface AporteRepository extends JpaRepository<Aporte, String> {
    
}
