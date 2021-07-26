package eu.lundegaard.contactform.repository;


import eu.lundegaard.contactform.repository.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {



}
