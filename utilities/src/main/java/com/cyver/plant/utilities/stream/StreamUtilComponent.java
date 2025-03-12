package com.cyver.plant.utilities.stream;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyver.plant.commons.dto.EnvironmentalMeasurementDto;
import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.utilities.map.MapUtilComponent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class StreamUtilComponent {

    private final MapUtilComponent mapUtilComponent;

    public List<PlantDto> plantStreamToListDto(Stream<Plant> plantStream) {
        return PlantStreamUtil.streamToListDto(plantStream, mapUtilComponent);
    }

    public List<EnvironmentalMeasurementDto> environmentalStreamToListDto(Stream<EnvironmentalMeasurement> environmentalMeasurementStream) {
        return EnvironmentalMeasurementStreamUtil.streamToListDto(environmentalMeasurementStream, mapUtilComponent);
    }

}
