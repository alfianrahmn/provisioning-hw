package com.voxloud.provisioning;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProvisioningApplication implements CommandLineRunner {


    @Autowired
    private DeviceRepository deviceRepository;


    public static void main(String[] args) {
        SpringApplication.run(ProvisioningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Devices in H2 Database ===");
        for (Device device : deviceRepository.findAll()) {
            System.out.println("MAC Address: " + device.getMacAddress() );
            System.out.println("Model: " + device.getModel()+" ..exists ");
            /*System.out.println("Username: " + device.getUsername());
            System.out.println("Password: " + device.getPassword());
            System.out.println("Override Fragment: " + device.getOverrideFragment());*/
            System.out.println("--------------------------------");
        }
    }

}