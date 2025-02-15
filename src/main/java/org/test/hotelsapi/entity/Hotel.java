package org.test.hotelsapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    private String description;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contacts contacts;
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrivalTime arrivalTime;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;

        Hotel hotel = (Hotel) obj;
        return StringUtils.equalsIgnoreCase(name, hotel.name)
                && StringUtils.equalsIgnoreCase(brand, hotel.brand)
                && StringUtils.equalsIgnoreCase(description, hotel.description)
                && address.equals(hotel.address)
                && contacts.equals(hotel.contacts)
                && arrivalTime.equals(hotel.arrivalTime)
                && amenities.equals(hotel.amenities);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, description, address, contacts, arrivalTime, amenities);
    }
}
