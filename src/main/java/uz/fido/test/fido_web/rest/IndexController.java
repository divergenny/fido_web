package uz.fido.test.fido_web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.fido.test.fido_web.dto.DocumentDTO;
import uz.fido.test.fido_web.service.DocumentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class IndexController {
    private final DocumentService documentService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("document", new DocumentDTO());
        return "document-form";
    }

    @PostMapping(value = "/save-document")
    public String saveDocument(@ModelAttribute DocumentDTO documentDTO,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               Model model) {
        System.out.println(documentDTO);
        String result = documentService.saveDocument(documentDTO, file);
        if (result.equals("Документ сохранен успешно!")) {
            model.addAttribute("successMessage", result);
            model.addAttribute("document", documentDTO);
            return "document-form";
        } else {
            model.addAttribute("errorMessage", result);
            return "document-form";
        }
    }
}
