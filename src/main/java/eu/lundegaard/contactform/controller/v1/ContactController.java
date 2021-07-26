package eu.lundegaard.contactform.controller.v1;

import eu.lundegaard.contactform.configuration.ContactFormConfig;
import eu.lundegaard.contactform.constant.StatusEnum;
import eu.lundegaard.contactform.dto.RequestDTO;
import eu.lundegaard.contactform.dto.RequestStatusDTO;
import eu.lundegaard.contactform.service.RequestService;
import eu.lundegaard.contactform.service.RequestTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contact-form")
public class ContactController {

    private final RequestTypeService requestTypeService;
    private final RequestService requestService;
    private final ContactFormConfig contactFormConfig;

    @GetMapping
    @ApiOperation(value = "Print contact form", notes = "Creates model of contact form and returns to the user.", produces = MediaType.TEXT_HTML_VALUE)
    public String printContactForm(Model model) {
        model.addAttribute("request", new RequestDTO());
        model.addAttribute("requestTypes", requestTypeService.getAllTypes());
        model.addAttribute("contactFormConfig", contactFormConfig);
        model.addAttribute("result", new RequestStatusDTO());
        return "contact";
    }

    @PostMapping
    @ApiOperation(value = "Handles contact form", notes = "Handles contact form and returns the result to the user.", produces = MediaType.TEXT_HTML_VALUE)
    public String handleContactForm(@ModelAttribute("request") RequestDTO request, Model model) {
        model.addAttribute("requestTypes", requestTypeService.getAllTypes());
        model.addAttribute("contactFormConfig", contactFormConfig);
        RequestStatusDTO result = requestService.createRequest(request);
        if(result.getStatus() == StatusEnum.SUCCESS){
            model.addAttribute("request", new RequestDTO());
        } else{
            model.addAttribute("request", request);
        }
        model.addAttribute("result", result);
        return "contact";
    }

}
