/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.application.service;

/**
 *
 * @author Diego
 */

import app.application.port.in.RecordVitalSignsUseCase;
import app.domain.model.VitalSignRecord;

import java.time.LocalDate;

public class RecordVitalSignsService implements RecordVitalSignsUseCase {

    // In a full implementation you'd inject a VitalSignsRepository (ports.out)
    public RecordVitalSignsService() {}

    @Override
    public VitalSignRecord record(RecordVitalSignsCommand cmd) {
        var rec = new VitalSignRecord();
        rec.setPatientIdNumber(cmd.patientIdNumber);
        rec.setDate(LocalDate.parse(cmd.dateIso));
        rec.setBloodPressure(cmd.bloodPressure);
        rec.setTemperatureC(cmd.temperatureC);
        rec.setPulseBpm(cmd.pulseBpm);
        rec.setOxygenSaturation(cmd.oxygenSaturation);

        // TODO: persist using VitalSignsRepository (ports.out) and validations
        return rec;
    }
}
