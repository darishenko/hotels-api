package org.test.hotelsapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.test.hotelsapi.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    boolean existsByNameIgnoreCase(String name);

    @Query("select hotel.brand, count(hotel) FROM Hotel hotel group by hotel.brand")
    List<Object[]> countByBrands();

    @Query("select address.city, count(hotel) from Address address left join Hotel hotel" +
            " on hotel.address = address group by address.city")
    List<Object[]> countByCities();

    @Query("select address.country, count(hotel) from Address address left join Hotel hotel" +
            " on hotel.address = address group by address.country")
    List<Object[]> countByCountries();

    @Query("select amenity.name, count(hotel) from Hotel hotel right join hotel.amenities amenity" +
            " group by amenity.name")
    List<Object[]> countByAmenities();
}
