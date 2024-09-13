package uz.fido.test.fido_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.fido.test.fido_web.model.IncomingDocument;

import java.time.LocalDate;
import java.util.List;

public interface IncomingDocumentRepository extends JpaRepository<IncomingDocument, Long> {
    List<IncomingDocument> findByRegDateBetween(LocalDate startDate, LocalDate endDate);

    List<IncomingDocument> findByCorrespondentAndDeliveryMethodAndRegDateBetween(
            String correspondent, String deliveryMethod, LocalDate startDate, LocalDate endDate);

    @Query("SELECT d FROM IncomingDocument d WHERE d.regDate BETWEEN :startDate AND :endDate "
            + "AND NOT ( d.correspondent = :correspondent AND d.deliveryMethod = :deliveryMethod)")
    List<IncomingDocument> findDocumentsForFirstQuarterExcludingGNIWithCourier(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("correspondent") String correspondent,
            @Param("deliveryMethod") String deliveryMethod);

    /**
     * Поиск документов по корреспонденту, дате регистрации и исключению по ключевому слову в теме.
     *
     * @param correspondent   корреспондент (ТСЖ).
     * @param startDate       начало периода (первый день предыдущего месяца).
     * @param endDate         конец периода (последний день предыдущего месяца).
     * @param keyword         ключевое слово для исключения (кредит).
     * @return Список документов, соответствующих критериям.
     */
    List<IncomingDocument> findByCorrespondentAndRegDateBetweenAndSubjectNotContainingIgnoreCase(
            String correspondent, LocalDate startDate, LocalDate endDate, String keyword);
}
