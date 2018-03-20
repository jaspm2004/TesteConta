package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Aporte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Aportes
 * 
 * @author Jose San Pedro
 */
@Repository
public interface AporteRepository extends JpaRepository<Aporte, String> {
    
    List<Aporte> findByContaId(Long contaId);
}
