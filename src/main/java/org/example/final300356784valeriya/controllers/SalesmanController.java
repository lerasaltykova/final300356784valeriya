package org.example.final300356784valeriya.controllers;

import org.example.final300356784valeriya.entities.Salesman;
import org.example.final300356784valeriya.repositories.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * For the full project code and documentation, please visit:
 * [GitHub Repository](https://github.com/lerasaltykova/final300356784valeriya)
 */
@Controller
public class SalesmanController {

    @Autowired
    private SalesmanRepository salesmanRepository;


     //Show a summary of all sales.

    @GetMapping("/sales")
    public String getSalesSummary(Model model) {
        // Retrieve all salesmen from the repository
        List<Salesman> salesmen = salesmanRepository.findAll();

        // Summarize sales by salesman and item
        Map<String, Map<String, Double>> salesSummary = salesmen.stream()
                .collect(Collectors.groupingBy(Salesman::getName,
                        Collectors.groupingBy(Salesman::getItem,
                                Collectors.summingDouble(Salesman::getAmount))));

        // Add data to the model
        model.addAttribute("salesmen", salesmen);
        model.addAttribute("salesSummary", salesSummary);
        model.addAttribute("newSalesman", new Salesman());
        return "sales";
    }

    // Save a new salesman to the repository.

    @PostMapping("/save-salesman")
    public String saveSalesman(@ModelAttribute("newSalesman") Salesman salesman) {
        salesmanRepository.save(salesman);
        return "redirect:/sales";
    }

    // Delete a salesman by ID.

    @GetMapping("/delete-salesman/{id}")
    public String deleteSalesman(@PathVariable Long id) {
        salesmanRepository.deleteById(id);
        return "redirect:/sales";
    }

    //Show the edit page for a salesman.

    @GetMapping("/edit-salesman/{id}")
    public String editSalesman(@PathVariable Long id, Model model) {
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        model.addAttribute("salesman", salesman);
        return "edit";
    }

    // Update an existing salesman.

    @PostMapping("/update-salesman")
    public String updateSalesman(@ModelAttribute("salesman") Salesman salesman) {
        if (salesman.getId() != null && salesmanRepository.existsById(salesman.getId())) {
            salesmanRepository.save(salesman);
        }
        return "redirect:/sales";
    }
}
