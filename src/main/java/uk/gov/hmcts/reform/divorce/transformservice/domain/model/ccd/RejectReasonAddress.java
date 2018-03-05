package uk.gov.hmcts.reform.divorce.transformservice.domain.model.ccd;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class RejectReasonAddress {
	
	@JsonProperty("RejectReasonAddressType")
	private String rejectReasonAddressType;

	@JsonProperty("RejectReasonAddressText")
	private String rejectReasonAddressText;

}
