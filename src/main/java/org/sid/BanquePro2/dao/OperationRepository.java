package org.sid.BanquePro2.dao;



import org.sid.BanquePro2.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	//@Query("select o from Operation o where o.compte.numComp=:x order by o.dataOperation desc")
	@Query("SELECT o FROM Operation o where o.compte.numComp=:x ORDER BY o.dataOperation desc")
	public Page<Operation> listOperation(@Param("x") String codeCpte, Pageable pageable);
}
