package eu.lundegaard.contactform.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "RequestType")
public class RequestTypeEntity {

    @Id
    @SequenceGenerator(name = "request_type_seq_id", sequenceName = "request_type_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_type_seq_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "requestType")
    private List<RequestEntity> requests = new ArrayList<>();
}
