/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Diego
 */

import java.math.BigDecimal;
import java.util.Optional;

public interface ProcedureCatalogRepository {
    boolean existsByName(String procedureName);
    Optional<BigDecimal> priceOf(String procedureName);
}
