package com.cyver.plant.utilities.stream;

import java.util.List;
import java.util.stream.Stream;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.utilities.map.MapUtilComponent;

class PlantStreamUtil {

    private PlantStreamUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static List<PlantDto> streamToListDto(Stream<Plant> plantStream, MapUtilComponent mapUtilComponent) {
        return plantStream.map(mapUtilComponent::toDto).toList();
    }

}
