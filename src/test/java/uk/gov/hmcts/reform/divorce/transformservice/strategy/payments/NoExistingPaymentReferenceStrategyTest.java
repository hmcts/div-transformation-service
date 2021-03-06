package uk.gov.hmcts.reform.divorce.transformservice.strategy.payments;

import org.junit.Test;
import uk.gov.hmcts.reform.divorce.transformservice.domain.model.ccd.PaymentCollection;
import uk.gov.hmcts.reform.divorce.transformservice.domain.model.divorceapplicationdata.Payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NoExistingPaymentReferenceStrategyTest {

    private NoExistingPaymentReferenceStrategy noExistingPaymentReferenceStrategy =
        new NoExistingPaymentReferenceStrategy();

    @Test
    public void testNoExistingPaymentReferenceAddsNewPayment() {
        PaymentCollection newPayment = createPayment("111222333");
        PaymentCollection existingPayment = createPayment("999888777");
        PaymentCollection anotherExistingPayment = createPayment("444555666");

        List<PaymentCollection> existingPaymentsList = new ArrayList<>();
        existingPaymentsList.add(existingPayment);
        existingPaymentsList.add(anotherExistingPayment);

        List<PaymentCollection> expectedPaymentsList = Arrays.asList(existingPayment, anotherExistingPayment,
            newPayment);
        List<PaymentCollection> returnedPaymentsList = noExistingPaymentReferenceStrategy
            .getCurrentPaymentsList(newPayment.getValue(), existingPaymentsList);

        assertThat(returnedPaymentsList, equalTo(expectedPaymentsList));
    }

    private PaymentCollection createPayment(String reference) {
        Payment payment = new Payment();
        payment.setPaymentReference(reference);

        return PaymentCollection.builder().value(payment).build();
    }
}
