package com.voxloud.provisioning.service.mapper;

import com.voxloud.provisioning.dto.DeviceDto;
import com.voxloud.provisioning.entity.Device;

public class DeviceMapper {

    public static DeviceDto toDeviceDto(Device device) {
        return new DeviceDto(device.getMacAddress(), device.getModel(), device.getUsername(), device.getPassword());
    }

}
