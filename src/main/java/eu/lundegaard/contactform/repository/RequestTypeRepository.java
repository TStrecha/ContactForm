package eu.lundegaard.contactform.repository;

import eu.lundegaard.contactform.repository.entity.RequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTypeRepository extends JpaRepository<RequestTypeEntity, Long> {


}
