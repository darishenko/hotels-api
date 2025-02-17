package org.test.hotelsapi.service.impl;

import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.test.hotelsapi.enums.HistogramParam;
import org.test.hotelsapi.repository.HotelRepository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultHistogramServiceTest {
    @InjectMocks
    private DefaultHistogramService histogramService;
    @Mock
    private HotelRepository hotelRepository;

    private List<Object[]> histogram;

    @Before
    public void setUp() {
        histogram = List.of(new Object[]{"Param value 1", 1L}, new Object[]{"Param value 2", 22L});

        ReflectionTestUtils.invokeMethod(histogramService, "init");
    }

    @Test
    public void getCountByHistogramParam_brandParam_returnBrandHistogram() {
        when(hotelRepository.countByBrands()).thenReturn(histogram);

        Map<String, Long> result = histogramService.getCountByHistogramParam(HistogramParam.brand);

        verify(hotelRepository).countByBrands();
        assertNotNull(result);
        assertEquals(histogram.size(), result.size());
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            String key = entry.getKey();
            assertTrue(result.containsKey(key));
            assertEquals(result.get(key), entry.getValue());
        }
    }

    @Test
    public void getCountByHistogramParam_cityParam_returnCityHistogram() {
        when(hotelRepository.countByCities()).thenReturn(histogram);

        Map<String, Long> result = histogramService.getCountByHistogramParam(HistogramParam.city);

        verify(hotelRepository).countByCities();
        assertNotNull(result);
        assertEquals(histogram.size(), result.size());
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            String key = entry.getKey();
            assertTrue(result.containsKey(key));
            assertEquals(result.get(key), entry.getValue());
        }
    }

    @Test
    public void getCountByHistogramParam_countryParam_returnCountryHistogram() {
        when(hotelRepository.countByCountries()).thenReturn(histogram);

        Map<String, Long> result = histogramService.getCountByHistogramParam(HistogramParam.country);

        verify(hotelRepository).countByCountries();
        assertNotNull(result);
        assertEquals(histogram.size(), result.size());
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            String key = entry.getKey();
            assertTrue(result.containsKey(key));
            assertEquals(result.get(key), entry.getValue());
        }
    }

    @Test
    public void getCountByHistogramParam_amenitiesParam_returnAmenitiesHistogram() {
        when(hotelRepository.countByAmenities()).thenReturn(histogram);

        Map<String, Long> result = histogramService.getCountByHistogramParam(HistogramParam.amenities);

        verify(hotelRepository).countByAmenities();
        assertNotNull(result);
        assertEquals(histogram.size(), result.size());
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            String key = entry.getKey();
            assertTrue(result.containsKey(key));
            assertEquals(result.get(key), entry.getValue());
        }
    }
}
