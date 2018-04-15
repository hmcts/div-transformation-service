package uk.gov.hmcts.reform.divorce.insights;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.TelemetryContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppInsightsTest {
    @Test
    public void test() {
        TelemetryContext telemetryContext = new TelemetryContext();
        telemetryContext.setInstrumentationKey("key");

        TelemetryClient telemetryClient = mock(TelemetryClient.class);
        when(telemetryClient.getContext()).thenReturn(telemetryContext);

        AppInsights appInsights = new AppInsights(telemetryClient);

        Assert.isInstanceOf(AppInsights.class, appInsights);
    }
}
