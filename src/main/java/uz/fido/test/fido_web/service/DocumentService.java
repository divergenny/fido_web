package uz.fido.test.fido_web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.fido.test.fido_web.dto.DocumentDTO;
import uz.fido.test.fido_web.model.IncomingDocument;
import uz.fido.test.fido_web.repo.IncomingDocumentRepository;
import uz.fido.test.fido_web.validation.DocumentServiceValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final IncomingDocumentRepository documentRepository;

    private final DocumentServiceValidator documentServiceValidator;

    public String saveDocument(String json, MultipartFile file) {
        String fileUpload = "";
        String checkRsl = documentServiceValidator.checkFileConditions(file);
        if (!checkRsl.equals("ok")) {
            return checkRsl;
        }
        try {
            String fileName = file.getOriginalFilename();
            String userDirectory = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .toString();
            String uploadDir = userDirectory + "/uploads/";
            File directory = new File(uploadDir + fileName);
            fileUpload = uploadDir + fileName;
            if (!directory.exists()) {
                directory.mkdir();
            }
            file.transferTo(directory);
        } catch (IOException e) {
            log.error("saveDocument" + e);
            return "Ошибка сохранения файла.";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        DocumentDTO documentDTO;
        try {
            documentDTO = objectMapper.readValue(json, DocumentDTO.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        checkRsl = documentServiceValidator.checkDocumentDTOConditions(documentDTO);
        if (!checkRsl.equals("ok")) {
            return checkRsl;
        }

        IncomingDocument incomingDocument = new IncomingDocument();
        incomingDocument.setRegNumber(documentDTO.getRegNumber());
        incomingDocument.setRegDate(LocalDate.parse(documentDTO.getRegDate()));
        incomingDocument.setOutDocNumber(documentDTO.getOutDocNumber());
        incomingDocument.setOutDocDate(LocalDate.parse(documentDTO.getOutDocDate()));
        incomingDocument.setDeliveryMethod(documentDTO.getDeliveryMethod());
        incomingDocument.setCorrespondent(documentDTO.getCorrespondent());
        incomingDocument.setSubject(documentDTO.getSubject());
        incomingDocument.setDescription(documentDTO.getDescription());
        incomingDocument.setDueDate(LocalDate.parse(documentDTO.getDueDate()));
        incomingDocument.setAccess(documentDTO.isAccess());
        incomingDocument.setControl(documentDTO.isControl());
        incomingDocument.setFileUpload(fileUpload);

        documentRepository.save(incomingDocument);
        return "Документ сохранен успешно!";
    }

    public String saveDocument(DocumentDTO documentDTO, MultipartFile file) {
        String fileUpload = "";
        String checkRsl = documentServiceValidator.checkFileConditions(file);
        if (!checkRsl.equals("ok")) {
            return checkRsl;
        }
        try {
            String fileName = file.getOriginalFilename();
            String userDirectory = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .toString();
            String uploadDir = userDirectory + "/uploads/";
            File directory = new File(uploadDir + fileName);
            fileUpload = uploadDir + fileName;
            if (!directory.exists()) {
                directory.mkdir();
            }
            file.transferTo(directory);
        } catch (IOException e) {
            log.error("saveDocument" + e);
            return "Ошибка сохранения файла.";
        }

        checkRsl = documentServiceValidator.checkDocumentDTOConditions(documentDTO);
        if (!checkRsl.equals("ok")) {
            return checkRsl;
        }

        IncomingDocument incomingDocument = new IncomingDocument();
        incomingDocument.setRegNumber(documentDTO.getRegNumber());
        incomingDocument.setRegDate(LocalDate.parse(documentDTO.getRegDate()));
        incomingDocument.setOutDocNumber(documentDTO.getOutDocNumber());
        incomingDocument.setOutDocDate(LocalDate.parse(documentDTO.getOutDocDate()));
        incomingDocument.setDeliveryMethod(documentDTO.getDeliveryMethod());
        incomingDocument.setCorrespondent(documentDTO.getCorrespondent());
        incomingDocument.setSubject(documentDTO.getSubject());
        incomingDocument.setDescription(documentDTO.getDescription());
        incomingDocument.setDueDate(LocalDate.parse(documentDTO.getDueDate()));
        incomingDocument.setAccess(documentDTO.isAccess());
        incomingDocument.setControl(documentDTO.isControl());
        incomingDocument.setFileUpload(fileUpload);

        documentRepository.save(incomingDocument);
        return "Документ сохранен успешно!";
    }

    public IncomingDocument createDocument(IncomingDocument document, MultipartFile file) {
        handleFileUpload(document, file);
        return documentRepository.save(document);
    }

    public Optional<IncomingDocument> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public List<IncomingDocument> getAllDocuments() {
        return documentRepository.findAll();
    }

    public IncomingDocument updateDocument(Long id,
                                           String json,
                                           MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentDTO updatedDocumentDTO;
        try {
            updatedDocumentDTO = objectMapper.readValue(json, DocumentDTO.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String checkRsl = documentServiceValidator.checkDocumentDTOConditions(updatedDocumentDTO);
        if (!checkRsl.equals("ok")) {
            return null;
            //return checkRsl;
        }
        Optional<IncomingDocument> existingDocument = documentRepository.findById(id);
        if (existingDocument.isPresent()) {
            IncomingDocument document = existingDocument.get();
            document.setRegNumber(updatedDocumentDTO.getRegNumber());
            document.setRegDate(LocalDate.parse(updatedDocumentDTO.getRegDate()));
            document.setOutDocNumber(updatedDocumentDTO.getOutDocNumber());
            document.setOutDocDate(LocalDate.parse(updatedDocumentDTO.getOutDocDate()));
            document.setDeliveryMethod(updatedDocumentDTO.getDeliveryMethod());
            document.setCorrespondent(updatedDocumentDTO.getCorrespondent());
            document.setSubject(updatedDocumentDTO.getSubject());
            document.setDescription(updatedDocumentDTO.getDescription());
            document.setDueDate(LocalDate.parse(updatedDocumentDTO.getDueDate()));
            document.setAccess(updatedDocumentDTO.isAccess());
            document.setControl(updatedDocumentDTO.isControl());
            handleFileUpload(document, file);
            return documentRepository.save(document);
        }
        return null;
    }

    public boolean deleteDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void handleFileUpload(IncomingDocument document, MultipartFile file) {
        String checkRsl = documentServiceValidator.checkFileConditions(file);
        if (!checkRsl.equals("ok")) {
            throw new IllegalArgumentException(checkRsl);
        }
        try {
            String fileName = file.getOriginalFilename();
            String userDirectory = FileSystems.getDefault()
                    .getPath("")
                    .toAbsolutePath()
                    .toString();
            String uploadDir = userDirectory + "\\uploads\\";
            System.out.println(uploadDir + fileName);
            File uploadFile = new File(uploadDir + fileName);
            if (!uploadFile.exists()) {
                uploadFile.mkdir();
            }
            file.transferTo(uploadFile);
            document.setFileUpload(uploadFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("handleFileUpload");
            throw new RuntimeException("Ошибка сохранения файла.", e);
        }
    }

    /**
     * Получает количество документов, сгруппированных по корреспонденту и месяцу за текущий год.
     * Получаю начало текущего года, текущую дата.
     * Затем получаю список документов за текущий год.
     * После, группирую по корреспонденту и месяцу (по корреспонденту, по месяцу и подсчитываю количество документов).
     *
     * @return Карта, где ключ — это имя корреспондента, а значение — другая карта,
     * где ключ — это YearMonth (год и месяц), а значение — количество документов за этот месяц.
     */
    public Map<String, Map<YearMonth, Long>> getCorrespondentMonthlyCount() {
        LocalDate startDate = LocalDate.now().withDayOfYear(1);
        LocalDate endDate = LocalDate.now();
        List<IncomingDocument> documents = documentRepository.findByRegDateBetween(startDate, endDate);
        return documents.stream()
                .collect(Collectors.groupingBy(
                        IncomingDocument::getCorrespondent,
                        Collectors.groupingBy(
                                doc -> YearMonth.from(doc.getRegDate()),
                                Collectors.counting()
                        )
                ));

    }

    /**
     * Получает список документов, полученных от ЦБ по Email за текущий месяц.
     * Получаю текущий месяц, первый день текущего месяца, последний день текущего месяца
     *
     * @return Список документов, полученных от ЦБ по Email за текущий месяц.
     */
    public List<IncomingDocument> getDocumentsFromCBByEmailForCurrentMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();
        return documentRepository.findByCorrespondentAndDeliveryMethodAndRegDateBetween(
                "ЦБ", "Email", startDate, endDate
        );
    }

    /**
     * Получает список документов за первый квартал текущего года,
     * исключая документы от ГНИ, доставленные курьером.
     * Получаю дату 1 января текущего года, затем дату 31 марта текущего года.
     *
     * @return Список документов за первый квартал, исключая документы от ГНИ, доставленные курьером.
     */
    public List<IncomingDocument> getDocumentsForFirstQuarterExcludingGNIWithCourier() {
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), Month.MARCH, 31);
        return documentRepository.findDocumentsForFirstQuarterExcludingGNIWithCourier(
                startDate, endDate, "ГНИ", "Курьер"
        );
    }

    /**
     * Получает список документов за предыдущий месяц, поступивших от ТСЖ,
     * исключая документы, в теме которых встречается слово "кредит".
     * Получаю предыдущий месяц, затем первый день предыдущего месяца,
     * после последний день предыдущего месяца
     *
     * @return Список документов от ТСЖ за предыдущий месяц,
     * исключая документы, связанные с кредитом.
     */
    public List<IncomingDocument> getDocumentsFromTSJExcludingCreditForPreviousMonth() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        LocalDate startDate = previousMonth.atDay(1);
        LocalDate endDate = previousMonth.atEndOfMonth();
        return documentRepository.findByCorrespondentAndRegDateBetweenAndSubjectNotContainingIgnoreCase(
                "ТСЖ", startDate, endDate, "кредит"
        );
    }
}
