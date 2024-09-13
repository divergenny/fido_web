package uz.fido.test.fido_web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DocumentDTO {
    private String regNumber;
    private String regDate;
    private String outDocNumber;
    private String outDocDate;
    private String deliveryMethod;
    private String correspondent;
    private String subject;
    private String description;
    private String dueDate;
    private boolean access;
    private boolean control;
    private String fileUpload;
}
