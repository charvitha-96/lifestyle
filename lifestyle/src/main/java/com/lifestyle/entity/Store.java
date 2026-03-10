package com.lifestyle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "stores")
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String phone;
    private boolean active = true;

    @ManyToOne(optional = false)
    private User owner;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getAddress() { return address; } public void setAddress(String address) { this.address = address; }
    public Double getLatitude() { return latitude; } public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; } public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
    public User getOwner() { return owner; } public void setOwner(User owner) { this.owner = owner; }
}