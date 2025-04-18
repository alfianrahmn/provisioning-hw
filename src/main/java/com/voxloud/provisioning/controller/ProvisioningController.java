package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/provisioning")
public class ProvisioningController {

    // DONE Implement controller method
    @Autowired
    private ProvisioningService provisioningService;

    @GetMapping("/{macAddress}")
    public ResponseEntity<String> getProvisioningFile(@PathVariable String macAddress) {
        String configurationFile = provisioningService.getProvisioningFile(macAddress);

        if(configurationFile == null || configurationFile.isEmpty())
        {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(configurationFile);
    }

}