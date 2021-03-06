package uk.gov.hmcts.reform.divorce.validationservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.gov.hmcts.reform.divorce.transformservice.domain.model.ccd.CoreCaseData;
import uk.gov.hmcts.reform.divorce.validationservice.client.ValidationClient;
import uk.gov.hmcts.reform.divorce.validationservice.domain.ValidationRequest;
import uk.gov.hmcts.reform.divorce.validationservice.domain.ValidationResponse;

import java.util.Map;

@Component
public class ValidationServiceImpl implements ValidationService {

    private static final String FORM_ID = "case-progression";

    @Autowired
    private ValidationClient validationClient;
    
    @Override
    public ValidationResponse validate(ValidationRequest request) {
        return validationClient.validate(request);
    }

    @Override
    public ValidationResponse validateCoreCaseData(CoreCaseData coreCaseData) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> caseData = objectMapper.convertValue(
            coreCaseData, new TypeReference<Map<String, Object>>() {}
        );

        ValidationRequest request = new ValidationRequest();

        request.setFormId(FORM_ID);
        request.setData(caseData);

        return validationClient.validate(request);
    }
}