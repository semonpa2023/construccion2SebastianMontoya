/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Diego
 */

import app.domain.model.StaffUser;
import java.util.Optional;

public interface StaffRepository {
    Optional<StaffUser> findByIdNumber(String idNumber);
}
