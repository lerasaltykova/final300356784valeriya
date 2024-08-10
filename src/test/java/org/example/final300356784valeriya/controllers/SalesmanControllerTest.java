package org.example.final300356784valeriya.controllers;

import org.example.final300356784valeriya.entities.Salesman;
import org.example.final300356784valeriya.repositories.SalesmanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SalesmanControllerTest {

    @Mock
    private SalesmanRepository salesmanRepository;

    @InjectMocks
    private SalesmanController salesmanController;

    @Mock
    private Model model;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSalesSummary() throws ParseException {
        Salesman salesman = new Salesman();
        salesman.setName("Alice Johnson");
        salesman.setItem("Washing Machine");
        salesman.setAmount(100.0);
        salesman.setDot(dateFormat.parse("2024-08-10"));

        when(salesmanRepository.findAll()).thenReturn(Collections.singletonList(salesman));

        String viewName = salesmanController.getSalesSummary(model);

        assertEquals("sales", viewName);
        verify(model).addAttribute(eq("salesmen"), any());
        verify(model).addAttribute(eq("salesSummary"), any());
        verify(model).addAttribute(eq("newSalesman"), any());
    }

    @Test
    void testSaveSalesman() throws ParseException {
        Salesman salesman = new Salesman();
        salesman.setName("Jessica Lam");
        salesman.setItem("Refrigerator");
        salesman.setAmount(150.0);
        salesman.setDot(dateFormat.parse("2024-08-10"));
        String viewName = salesmanController.saveSalesman(salesman);
        assertEquals("redirect:/sales", viewName);
        verify(salesmanRepository).save(salesman);
    }

    @Test
    void testDeleteSalesman() {
        Long id = 1L;

        String viewName = salesmanController.deleteSalesman(id);
        assertEquals("redirect:/sales", viewName);
        verify(salesmanRepository).deleteById(id);
    }

    @Test
    void testEditSalesman() {
        Long id = 1L;
        Salesman salesman = new Salesman();
        salesman.setId(id);
        salesman.setName("John Doe");

        when(salesmanRepository.findById(id)).thenReturn(Optional.of(salesman));

        String viewName = salesmanController.editSalesman(id, model);

        assertEquals("edit", viewName);
        verify(model).addAttribute(eq("salesman"), any());
    }

    @Test
    void testUpdateSalesman() {
        Salesman salesman = new Salesman();
        salesman.setId(1L);
        salesman.setName("John Doe Updated");

        when(salesmanRepository.existsById(salesman.getId())).thenReturn(true);
        String viewName = salesmanController.updateSalesman(salesman);

        assertEquals("redirect:/sales", viewName);
        verify(salesmanRepository).save(salesman);
    }
}
