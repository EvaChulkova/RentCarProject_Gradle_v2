package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.dto.car.CarCreateEditDto;
import jane.rentcarproject_gradle.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("cars", carService.findAll());

        return "car/cars";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        return carService.findById(id)
                .map(car -> {
                    model.addAttribute("car", car);
                    model.addAttribute("colors", CarColorEnum.values());
                    model.addAttribute("statuses", CarStatusEnum.values());
                    return "car/car";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute CarCreateEditDto carCreateEditDto) {

        return "redirect:/cars/" + carService.create(carCreateEditDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute CarCreateEditDto carCreateEditDto) {

        return carService.update(id, carCreateEditDto)
                .map(it -> "redirect:/cars/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!carService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/cars";
    }
}
