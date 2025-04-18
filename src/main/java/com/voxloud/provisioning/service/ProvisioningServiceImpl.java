package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public String getProvisioningFile(String macAddress) {
        // DONE Implement provisioning

        // Step 1: Fetch device by MAC address
        Optional<Device> device = deviceRepository.findById(macAddress);

        if (!device.isPresent()) {
            System.out.println("device not found");
           return null;
        }
        System.out.println(device.toString());

        // Step 2: Generate configuration file based on model and override fragment
        Device dev = device.get();
        if (dev.getModel() == Device.DeviceModel.DESK) {
            /// Generate Property file format for DESK
            StringBuilder configBuilder = new StringBuilder();
            configBuilder.append("username=").append(dev.getUsername()).append("\n");
            configBuilder.append("password=").append(dev.getPassword()).append("\n");

            if (dev.getOverrideFragment() != null) {
                configBuilder.append(dev.getOverrideFragment()).append("\n");
            } else {
                configBuilder.append("domain=sip.voxloud.com\n");
                configBuilder.append("port=5060\n");
                configBuilder.append("codecs=G711,G729,OPUS\n");
            }

            System.out.println("Generated configuration DESK");
            return configBuilder.toString();

        } else if (dev.getModel() == Device.DeviceModel.CONFERENCE) {
            // Generate JSON file format for CONFERENCE
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("  \"username\" : \"").append(dev.getUsername()).append("\",\n");
            jsonBuilder.append("  \"password\" : \"").append(dev.getPassword()).append("\",\n");

            if (dev.getOverrideFragment() != null) {
                jsonBuilder.append(dev.getOverrideFragment().replace("\n", ",\n")).append(",\n");
            } else {
                jsonBuilder.append("  \"domain\" : \"sip.voxloud.com\",\n");
                jsonBuilder.append("  \"port\" : \"5060\",\n");
                jsonBuilder.append("  \"codecs\" : [\"G711\",\"G729\",\"OPUS\"]\n");
            }

            jsonBuilder.append("}");

            System.out.println("Generated configuration CONFERENCE");
            return jsonBuilder.toString();

        }else {
            return "Invalid device model when creating configuration";
        }
    }
}
