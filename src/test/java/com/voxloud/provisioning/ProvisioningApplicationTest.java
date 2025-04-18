package com.voxloud.provisioning;

import com.voxloud.provisioning.repository.DeviceRepository;
import com.voxloud.provisioning.service.ProvisioningServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest // Memastikan aplikasi Spring Boot berjalan untuk mengakses database
@ExtendWith(MockitoExtension.class)
public class ProvisioningApplicationTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ProvisioningServiceImpl provisioningService;

    @Test
    void shouldProvisionDeskPhoneWithoutOverride() {
        String result = provisioningService.getProvisioningFile("aa-bb-cc-dd-ee-ff");
        System.out.println(result);
        assertTrue(result.contains("username=john"));
        assertTrue(result.contains("password=doe"));
    }

    @Test
    void shouldProvisionConferencePhoneWithoutOverride() {
        String result = provisioningService.getProvisioningFile("f1-e2-d3-c4-b5-a6");

        assertTrue(result.contains("\"username\" : \"sofia\""));
        assertTrue(result.contains("\"password\" : \"red\""));
        assertTrue(result.contains("\"domain\" : \"sip.voxloud.com\""));
        assertTrue(result.contains("\"port\" : \"5060\""));
        assertTrue(result.contains("\"codecs\" : [\"G711\",\"G729\",\"OPUS\"]"));
    }

    @Test
    void shouldProvisionDeskPhoneWithOverride() {
        String result = provisioningService.getProvisioningFile("a1-b2-c3-d4-e5-f6");

        assertTrue(result.contains("username=walter"));
        assertTrue(result.contains("password=white"));
        assertTrue(result.contains("domain=sip.anotherdomain.com"));
        assertTrue(result.contains("port=5161"));
        assertTrue(result.contains("timeout=10"));
    }

    @Test
    void shouldProvisionConferencePhoneWithOverride() {
        String result = provisioningService.getProvisioningFile("1a-2b-3c-4d-5e-6f");

        assertTrue(result.contains("\"username\" : \"eric\""));
        assertTrue(result.contains("\"password\" : \"blue\""));
        assertTrue(result.contains("\"domain\" : \"sip.anotherdomain.com\""));
        assertTrue(result.contains("\"port\" : \"5161\""));
        assertTrue(result.contains("\"timeout\" : 10"));
    }

    @Test
    void shouldReturnNotFoundForNonExistingDevice() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            provisioningService.getProvisioningFile("aa-aa-aa-aa-aa-aa");
        });

        assertTrue(exception.getMessage().contains("Device not found in inventory"));
    }
}