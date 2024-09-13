package uz.fido.test.fido_web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.fido.test.fido_web.model.IncomingDocument;
import uz.fido.test.fido_web.service.DocumentService;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveDocument(@RequestParam(value = "data") String documentDTO,
                                               @RequestParam(value = "file", required = false) MultipartFile file) {

        String result = documentService.saveDocument(documentDTO, file);

        if (result.equals("Документ сохранен успешно!")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomingDocument> getDocumentById(@PathVariable Long id) {
        Optional<IncomingDocument> document = documentService.getDocumentById(id);
        return document.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping
    public ResponseEntity<List<IncomingDocument>> getAllDocuments() {
        List<IncomingDocument> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomingDocument> updateDocument(@PathVariable Long id,
                                                           @RequestParam(value = "data") String json,
                                                           @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            IncomingDocument updatedDocument = documentService.updateDocument(id, json, file);
            if (updatedDocument != null) {
                return ResponseEntity.ok(updatedDocument);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        if (documentService.deleteDocument(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/correspondent-count")
    public Map<String, Map<YearMonth, Long>> getCorrespondentMonthlyCount() {
        return documentService.getCorrespondentMonthlyCount();
    }

    @GetMapping("/cb-email-documents-current-month")
    public List<IncomingDocument> getDocumentsFromCBByEmailForCurrentMonth() {
        return documentService.getDocumentsFromCBByEmailForCurrentMonth();
    }

    @GetMapping("/first-quarter-excluding-gni-courier")
    public List<IncomingDocument> getDocumentsForFirstQuarterExcludingGNIWithCourier() {
        return documentService.getDocumentsForFirstQuarterExcludingGNIWithCourier();
    }

    @GetMapping("/previous-month-tsj-excluding-credit")
    public List<IncomingDocument> getDocumentsFromTSJExcludingCreditForPreviousMonth() {
        return documentService.getDocumentsFromTSJExcludingCreditForPreviousMonth();
    }
}
