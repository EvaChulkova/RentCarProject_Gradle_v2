package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.dto.client.ClientCreateEditDto;
import jane.rentcarproject_gradle.service.ClientService;
import jane.rentcarproject_gradle.service.UserService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final UserService userService;
    private final ClientService clientService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("clients", clientService.findAll());

        return "client/clients";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {

        return clientService.findById(id)
                .map(client -> {
                    model.addAttribute("client", client);
                    model.addAttribute("user", userService.findAll());

                    return "client/client";
                })
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/createClient")
    public String create(Model model,
                         ClientCreateEditDto clientCreateEditDto) {

        model.addAttribute("client", clientCreateEditDto);
        model.addAttribute("users", userService.findAll());

        return "client/createClient";
    }

    @PostMapping
    public String create(@Valid ClientCreateEditDto clientCreateEditDto) {

        return "redirect:/clients/" + clientService.create(clientCreateEditDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute ClientCreateEditDto clientCreateEditDto) {

        return clientService.update(id, clientCreateEditDto)
                .map(it -> "redirect:/clients/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!clientService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/clients";
    }
}
