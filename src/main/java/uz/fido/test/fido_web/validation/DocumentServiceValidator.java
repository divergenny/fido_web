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
        if (file == null || file.isEmpty()) {
            return "Выберите файл";
        } else {
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
        StringBuilder responseBuilder = new StringBuilder();
        int index = 0;

        if (documentDTO.getRegNumber().isEmpty()
                || documentDTO.getRegDate() == null
                || documentDTO.getCorrespondent().isEmpty()
                || documentDTO.getSubject().isEmpty()) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Проверьте поля: Регистрационный №, Дата регистрации, Корреспондент, Тема.");
        }
        if (!Pattern.matches("^(?=.*[0-9])[0-9a-zA-Z\\p{Punct}]+$", documentDTO.getRegNumber())) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Регистрационный № должен содержать цифры и буквы или специальные символы, и не может состоять только из букв или специальных символов");
        }
        LocalDate regDate = LocalDate.parse(documentDTO.getRegDate());
        if (!regDate.equals(LocalDate.now())) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Дата регистрации должна быть текущей датой");
        }
        if (documentDTO.getOutDocNumber() != null
                && !documentDTO.getOutDocNumber().isEmpty()) {
            if (!Pattern.matches("^(?=.*[0-9])[0-9a-zA-Z\\p{Punct}]+$", documentDTO.getOutDocNumber())) {
                index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Номер исходного документа должен содержать цифры и буквы или специальные символы, и не может состоять только из букв или специальных символов");
            }
        }
        String deliveryMethod = documentDTO.getDeliveryMethod();
        if (!deliveryMethod.equals("Курьер") && !deliveryMethod.equals("Email") && !deliveryMethod.equals("Телефонограмма")) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Форма доставки должна быть одной из: Курьер, Email, Телефонограмма");
        }
        String correspondent = documentDTO.getCorrespondent();
        if (!correspondent.equals("ЦБ") && !correspondent.equals("ГНИ") && !correspondent.equals("ТСЖ")) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Корреспондент должен быть одним из: ЦБ, ГНИ, ТСЖ");
        }
        if (documentDTO.getSubject().length() > 100) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Тема не может быть длиннее 100 знаков");
        }
        if (documentDTO.getDescription() != null
                && documentDTO.getDescription().length() > 1000) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Описание не может быть длиннее 1000 знаков");
        }
        LocalDate dueDate = LocalDate.parse(documentDTO.getDueDate());
        if (dueDate.isBefore(regDate)) {
            index = formCheckDocumentDTOConditionsResponse(responseBuilder, index, "Срок исполнения не может быть раньше даты регистрации документа");
        }
        return responseBuilder.toString();
    }

    private int formCheckDocumentDTOConditionsResponse(StringBuilder responseBuilder, int index, String message) {
        index++;
        responseBuilder.append("\n").append(index).append(") ").append(message);
        return index;
    }
}
