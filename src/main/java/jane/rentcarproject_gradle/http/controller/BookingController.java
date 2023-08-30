package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.dto.booking.BookingCreateEditDto;
import jane.rentcarproject_gradle.service.BookingService;
import jane.rentcarproject_gradle.service.CarService;
import jane.rentcarproject_gradle.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final ClientService clientService;
    private final CarService carService;
    private final BookingService bookingService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("bookings", bookingService.findAll());

        return "booking/bookings";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {

        return bookingService.findById(id)
                .map(booking -> {
                    model.addAttribute("booking", booking);
                    model.addAttribute("clients", clientService.findAll());
                    model.addAttribute("cars", carService.findAll());

                    return "booking/booking";
                })
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/createBooking")
    public String create(Model model,
                         BookingCreateEditDto bookingCreateEditDto) {

        model.addAttribute("booking", bookingCreateEditDto);
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("cars", carService.findAll());

        return "booking/createBooking";
    }

    @PostMapping
    public String create(@Valid BookingCreateEditDto bookingCreateEditDto) {
        return "redirect:/bookings/" + bookingService.create(bookingCreateEditDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute BookingCreateEditDto bookingCreateEditDto) {

        return bookingService.update(id, bookingCreateEditDto)
                .map(it -> "redirect:/bookings/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!bookingService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/bookings";
    }
}
