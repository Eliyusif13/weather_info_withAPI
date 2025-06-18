package com.sadiqov.weatherforecast.controller;
import com.sadiqov.weatherforecast.dto.CityDto;
import com.sadiqov.weatherforecast.dto.WeatherDTO;
import com.sadiqov.weatherforecast.entity.City;
import com.sadiqov.weatherforecast.entity.Weather;
import com.sadiqov.weatherforecast.repository.CityRepository;
import com.sadiqov.weatherforecast.repository.WeatherRepository;
import com.sadiqov.weatherforecast.service.CityService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;

    private final CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<?> createCity(@RequestBody CityDto cityDto) {

        return ResponseEntity.ok(cityService.saveCity(cityDto));
    }

    @GetMapping("/weather")
    public List<WeatherDTO> getLatestWeather() {
        List<City> cities = cityRepository.findAll();
        List<WeatherDTO> result = new ArrayList<>();
        for (City city : cities) {
            Weather latest = weatherRepository.findTop24ByCityOrderByTimestampDesc(city)
                    .stream().findFirst().orElse(null);
            if (latest != null) {
                WeatherDTO dto = new WeatherDTO();
                dto.setCityName(city.getName());
                dto.setTemperature(latest.getTemperature());
                dto.setHumidity(latest.getHumidity());
                dto.setDescription(latest.getDescription());
                dto.setTimestamp(latest.getTimestamp());
                result.add(dto);
            }
        }
        return result;
    }

    @GetMapping("/weather/export")
    public void exportWeatherToExcel(HttpServletResponse response,
                                     @RequestParam String cityName) throws IOException {

        City city = cityRepository.findByName(cityName)
                .orElseThrow(() -> new RuntimeException("City not found"));

        List<Weather> data = weatherRepository.findTop24ByCityOrderByTimestampDesc(city);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Weather");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Time");
        header.createCell(1).setCellValue("Temp");
        header.createCell(2).setCellValue("Humidity");
        header.createCell(3).setCellValue("Description");

        int i = 1;
        for (Weather weather : data) {
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(weather.getTimestamp().toString());
            row.createCell(1).setCellValue(weather.getTemperature());
            row.createCell(2).setCellValue(weather.getHumidity());
            row.createCell(3).setCellValue(weather.getDescription());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=weather.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}


