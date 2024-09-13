package uz.fido.test.fido_web.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class IncomingDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String regNumber;

    @Column(nullable = false)
    private LocalDate regDate;

    private String outDocNumber;
    private LocalDate outDocDate;
    private String deliveryMethod;

    @Column(nullable = false)
    private String correspondent;

    @Column(nullable = false, length = 100)
    private String subject;

    @Column(length = 1000)
    private String description;

    private LocalDate dueDate;
    private boolean access;
    private boolean control;
    private String fileUpload;
}
