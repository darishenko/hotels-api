package org.test.hotelsapi.service;

import java.util.Map;
import org.test.hotelsapi.enums.HistogramParam;

public interface HistogramService {
    Map<String, Long> getCountByHistogramParam(HistogramParam histogramParam);
}
