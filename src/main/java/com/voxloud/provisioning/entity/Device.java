package com.voxloud.provisioning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {

    @Id
    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceModel model;

    @Column(name = "override_fragment")
    private String overrideFragment;

    private String username;

    private String password;

    public enum DeviceModel {
        CONFERENCE,
        DESK
    }
}