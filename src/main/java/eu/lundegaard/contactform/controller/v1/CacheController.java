package eu.lundegaard.contactform.controller.v1;

import eu.lundegaard.contactform.dto.StatusDTO;
import eu.lundegaard.contactform.service.RequestTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/cache/")
public class CacheController {

    private final RequestTypeService requestTypeService;

    @PutMapping("request-type/reset")
    @ApiOperation(value = "Emit request type cache", notes = "Emits the request type cache.", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusDTO resetRequestTypeCache(){
        return requestTypeService.resetRequestTypeCache();
    }

}
