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
public class PageFormController {
    private final DocumentService documentService;

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("formName", "");
        model.addAttribute("buttonStylePrintDisplay", "display:none");
        model.addAttribute("documentFormData", new DocumentDTO());
        return "document-form";
    }

    @PostMapping(value = "/save-document")
    public String saveDocument(@ModelAttribute DocumentDTO documentDTO,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               Model model) {
        if (null == documentDTO) {
            return "redirect:/page";
        }
        String result = documentService.saveDocument(documentDTO, file);
        if (null != file) {
            documentDTO.setFileUpload(file.getOriginalFilename());
        }
        model.addAttribute("alertMessage", result);
        model.addAttribute("documentFormData", documentDTO);
        model.addAttribute("nameOfFileUpload", documentDTO.getFileUpload());
        if (result.equals("Документ сохранен успешно!")) {
            model.addAttribute("buttonStylePrintDisplay", "display:true");
            model.addAttribute("typeOfResponseAlertMessage", "success");
        } else {
            model.addAttribute("typeOfResponseAlertMessage", "danger");
            model.addAttribute("buttonStylePrintDisplay", "display:none");
        }
        return "document-form";
    }
}
