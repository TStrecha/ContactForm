package eu.lundegaard.contactform.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "request")
public class RequestEntity {

    @Id
    @SequenceGenerator(name = "request_seq_id", sequenceName = "request_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_id")
    private Long id;

    @ManyToOne
    private RequestTypeEntity requestType;

    private String policyNumber;
    private String name;
    private String surname;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String message;

}
