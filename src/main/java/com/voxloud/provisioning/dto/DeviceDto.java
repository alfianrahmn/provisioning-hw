package com.voxloud.provisioning.dto;


import com.voxloud.provisioning.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDto {
    private String macAddress;
    private Device.DeviceModel model;
    private String username;
    private String password;
}