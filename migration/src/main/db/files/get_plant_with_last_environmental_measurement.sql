CREATE OR REPLACE FUNCTION get_plant_with_last_environmental_measurement(owner_uuid uuid)
    RETURNS SETOF plant_with_last_environmental_measurement AS
$$
DECLARE
    _current    plant_with_last_environmental_measurement;
    _owner_uuid uuid = owner_uuid;
    _plant      record;
begin
    FOR _plant IN
        SELECT p0.plant_uuid,
               p0.plant_name,
               p0.latitude,
               p0.longitude,
               t0.type_name
        FROM plants p0
                 JOIN types t0 ON t0.type_uuid = p0.type_uuid
        WHERE p0.owner_uuid = _owner_uuid
        ORDER BY p0.plant_name DESC
        LOOP
            _current.plant_name := _plant.plant_name;
            _current.latitude := _plant.latitude;
            _current.longitude := _plant.longitude;
            _current.type_name := _plant.type_name;
            SELECT em1.measured_at,
                   em1.temperature,
                   em1.humidity,
                   em1.light,
                   em1.soil_moisture,
                   em1.heat_index,
                   em1.unit
            INTO _current.measured_at,
                 _current.temperature,
                 _current.humidity,
                 _current.light,
                 _current.soil_moisture,
                 _current.heat_index,
                 _current.unit
            FROM environmental_measurements em1
            WHERE em1.plant_uuid = _plant.plant_uuid
                ORDER BY em1.measured_at DESC
            LIMIT 1;
            return next _current;
        end loop;
    return;
end;
$$ LANGUAGE plpgsql
    SECURITY DEFINER;
