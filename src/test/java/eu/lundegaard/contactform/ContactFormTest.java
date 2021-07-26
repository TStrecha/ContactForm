package eu.lundegaard.contactform;

import eu.lundegaard.contactform.constant.StatusEnum;
import eu.lundegaard.contactform.constant.ValidationErrorConstants;
import eu.lundegaard.contactform.dto.RequestDTO;
import eu.lundegaard.contactform.dto.RequestStatusDTO;
import eu.lundegaard.contactform.dto.UserValidationErrorDTO;
import eu.lundegaard.contactform.repository.RequestTypeRepository;
import eu.lundegaard.contactform.repository.entity.RequestTypeEntity;
import eu.lundegaard.contactform.service.RequestService;
import eu.lundegaard.contactform.service.RequestTypeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ContactFormTest {

    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestTypeService requestTypeService;
    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Test
    @Order(0)
    void requestType_createNew_success() {
        int requestTypeCount = requestTypeService.getAllTypes().size();

        createRequestType();

        assertEquals(requestTypeCount + 1, requestTypeService.getAllTypes().size());
    }

    @Test
    @Order(1)
    void request_createNew_success() {
        RequestTypeEntity type = createRequestType();

        RequestDTO request = new RequestDTO();
        request.setRequestType(type.getId());
        request.setEmail("example@gmail.com");
        request.setName("James");
        request.setSurname("Smith");
        request.setPolicyNumber("2U37828");
        request.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ultricies scelerisque enim, in dictum nibh congue quis. Quisque ut euismod ligula. Quisque consequat leo arcu, id porta diam condimentum vitae. Nullam semper eros at felis tincidunt, et bibendum lectus aliquam. Nullam vulputate risus quis semper dictum. Proin sit amet convallis odio. Sed iaculis, lacus non tristique posuere, metus tellus vulputate leo, et dignissim massa turpis eget nunc. Fusce vitae odio non nisl vehicula facilisis sed a libero. Mauris molestie neque turpis, quis interdum libero gravida efficitur. Nam in nisi dignissim, euismod elit a, convallis sem. Quisque ultrices erat diam, quis lobortis eros pulvinar ac. Quisque ligula mi, malesuada vel nulla non, consequat pharetra nulla. Sed nisi sapien, consectetur eget molestie non, cursus vitae orci. Cras fermentum leo quis ex euismod ornare. Fusce porttitor, elit id dictum venenatis, ante nunc tempus risus, et volutpat neque dolor vitae turpis.");

        RequestStatusDTO result = requestService.createRequest(request);

        assertEquals(StatusEnum.SUCCESS, result.getStatus());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    @Order(2)
    void request_createNew_fail() {
        RequestTypeEntity type = createRequestType();

        RequestDTO request = new RequestDTO();
        request.setRequestType(type.getId());
        request.setEmail("testingemail");
        request.setName("Thomas1");
        request.setSurname("Smith");
        request.setPolicyNumber("23867A");
        request.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ultricies scelerisque enim, in dictum nibh congue quis. Quisque ut euismod ligula. Quisque consequat leo arcu, id porta diam condimentum vitae. Nullam semper eros at felis tincidunt, et bibendum lectus aliquam. Nullam vulputate risus quis semper dictum. Proin sit amet convallis odio. Sed iaculis, lacus non tristique posuere, metus tellus vulputate leo, et dignissim massa turpis eget nunc. Fusce vitae odio non nisl vehicula facilisis sed a libero. Mauris molestie neque turpis, quis interdum libero gravida efficitur. Nam in nisi dignissim, euismod elit a, convallis sem. Quisque ultrices erat diam, quis lobortis eros pulvinar ac. Quisque ligula mi, malesuada vel nulla non, consequat pharetra nulla. Sed nisi sapien, consectetur eget molestie non, cursus vitae orci. Cras fermentum leo quis ex euismod ornare. Fusce porttitor, elit id dictum venenatis, ante nunc tempus risus, et volutpat neque dolor vitae turpis.");

        RequestStatusDTO result = requestService.createRequest(request);

        assertEquals(StatusEnum.FAILURE, result.getStatus());
        assertEquals(2, result.getErrors().size());

        for(UserValidationErrorDTO error : result.getErrors()){
            assertTrue(error.getErrorCode() == ValidationErrorConstants.INVALID_NAME || error.getErrorCode() == ValidationErrorConstants.INVALID_EMAIL);
        }
    }

    private RequestTypeEntity createRequestType(){
        RequestTypeEntity requestTypeEntity = new RequestTypeEntity();
        requestTypeEntity.setName("Test1");

        RequestTypeEntity result = requestTypeRepository.save(requestTypeEntity);
        requestTypeService.resetRequestTypeCache();
        return result;
    }

}
