package org.example.final300356784valeriya.repositories;

import org.example.final300356784valeriya.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    //Find salesmen by their ID.
     // returns list of Salesman entities with the given ID.

    List<Salesman> findSalesmanById(long kw);
}
