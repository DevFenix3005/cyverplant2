package com.cyver.plant.utilities.stream;

import java.util.List;
import java.util.stream.Stream;

import com.cyver.plant.commons.dto.EnvironmentalMeasurementDto;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.utilities.map.MapUtilComponent;

class EnvironmentalMeasurementStreamUtil {

    private EnvironmentalMeasurementStreamUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static List<EnvironmentalMeasurementDto> streamToListDto(Stream<EnvironmentalMeasurement> environmentalMeasurementStream,
            MapUtilComponent mapUtilComponent) {
        return environmentalMeasurementStream.map(mapUtilComponent::toDto).toList();
    }

}
