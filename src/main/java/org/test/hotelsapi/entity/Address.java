package org.test.hotelsapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    private int houseNumber;
    @Column(nullable = false)
    private String postCode;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;

        Address address = (Address) obj;

        return StringUtils.equalsIgnoreCase(country, address.country)
                && StringUtils.equalsIgnoreCase(city, address.city)
                && StringUtils.equalsIgnoreCase(street, address.street)
                && houseNumber == address.houseNumber
                && StringUtils.equalsIgnoreCase(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, houseNumber, postCode);
    }
}
