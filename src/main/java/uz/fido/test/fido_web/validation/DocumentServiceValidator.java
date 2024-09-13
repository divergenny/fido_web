package uz.fido.test.fido_web.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.fido.test.fido_web.dto.DocumentDTO;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class DocumentServiceValidator {

    /**
     * File validation logic
     *
     * @param file - MultipartFile
     * @return result;
     */
    public String checkFileConditions(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            boolean extensionOfFile = !fileName.endsWith(".pdf") && !fileName.endsWith(".doc") && !fileName.endsWith(".docx");
            boolean sizeOfFile = file.getSize() > 1048576;

            if (extensionOfFile && sizeOfFile) {
                return "Недопустимый формат и размер файла";
            } else if (extensionOfFile) {
                return "Недопустимый формат файла.";
            } else if (sizeOfFile) {
                return "Размер файла превышает 1Мб.";
            }
        }
        return "ok";
    }

    public String checkDocumentDTOConditions(DocumentDTO documentDTO) {
        if (documentDTO.getRegNumber().isEmpty()
                || documentDTO.getRegDate() == null
                || documentDTO.getCorrespondent().isEmpty()
                || documentDTO.getSubject().isEmpty()) {
            return "Заполните выделенные поля!";
        }
        if (!Pattern.matches("^(?=.*[0-9])[0-9a-zA-Z\\p{Punct}]+$", documentDTO.getRegNumber())) {
            return "Рег. № должен содержать цифры и буквы или специальные символы, и не может состоять только из букв или специальных символов";
        }
        LocalDate regDate = LocalDate.parse(documentDTO.getRegDate());
        if (!regDate.equals(LocalDate.now())) {
            return "Дата рег. должна быть текущей датой";
        }
        if (documentDTO.getOutDocNumber() != null
                && !documentDTO.getOutDocNumber().isEmpty()) {
            if (!Pattern.matches("^(?=.*[0-9])[0-9a-zA-Z\\p{Punct}]+$", documentDTO.getOutDocNumber())) {
                return "№ исх. док-та должен содержать цифры и буквы или специальные символы, и не может состоять только из букв или специальных символов";
            }
        }
        String deliveryMethod = documentDTO.getDeliveryMethod();
        if (!deliveryMethod.equals("Courier") && !deliveryMethod.equals("Email") && !deliveryMethod.equals("Phonegram")) {
            return "Форма доставки должна быть одной из: Курьер, Email, Телефонограмма";
        }
        String correspondent = documentDTO.getCorrespondent();
        if (!correspondent.equals("ЦБ") && !correspondent.equals("ГНИ") && !correspondent.equals("ТСЖ")) {
            return "Корреспондент должен быть одним из: ЦБ, ГНИ, ТСЖ";
        }
        if (documentDTO.getSubject().length() > 100) {
            return "Тема не может быть длиннее 100 знаков";
        }
        if (documentDTO.getDescription() != null
                && documentDTO.getDescription().length() > 1000) {
            return "Описание не может быть длиннее 1000 знаков";
        }
        LocalDate dueDate = LocalDate.parse(documentDTO.getDueDate());
        if (dueDate.isBefore(regDate)) {
            return "Срок исполнения не может быть раньше даты регистрации документа";
        }
        return "ok";
    }
}
