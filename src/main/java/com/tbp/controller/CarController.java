package com.tbp.controller;


import com.tbp.repository.Car;
import com.tbp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("secure")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping( value = "carCreate", method = RequestMethod.GET)
    public String createPage() {
        return "secure/carCreatePage";
    }

    @RequestMapping( value = "carCreate", method = RequestMethod.POST)
    public String save(@RequestParam("name") String name,
                       @RequestParam("year") Integer year,
                       @RequestParam("brand") String brand,
                       @RequestParam("color") String color,
                       Map<String, Object> model) {

        if(StringUtils.hasText(name) && year != null && StringUtils.hasText(brand) && StringUtils.hasText(color)) {
            Car car = new Car(brand, year, color, name);
            carRepository.save(car);
            model.put("message", "Car " + name + " saved");
            return "secure/carCreatePage";
        }
        return null;
    }

    @RequestMapping(value = "carList", method = RequestMethod.GET)
    public String listPage(Map<String, Object> model ) {
        List<Car> carList = carRepository.findAll();
        model.put("carList", carList);
        return "secure/carListPage";
    }


}
