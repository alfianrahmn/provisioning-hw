package com.voxloud.provisioning.service;

public interface ProvisioningService {

    /**
     * read file provisioning from macAddress
     *  when macAddress not found return null
     *
     * @param macAddress
     * @return
     */
    String getProvisioningFile(String macAddress);
}
