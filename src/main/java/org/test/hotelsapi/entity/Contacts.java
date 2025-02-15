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
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;

        Contacts contacts = (Contacts) obj;

        return StringUtils.equalsIgnoreCase(phone, contacts.phone)
                && StringUtils.equalsIgnoreCase(email, contacts.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}
