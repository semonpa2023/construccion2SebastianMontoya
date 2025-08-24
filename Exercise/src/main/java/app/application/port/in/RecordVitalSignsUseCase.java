/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */

import app.domain.model.VitalSignRecord;

public interface RecordVitalSignsUseCase {
    VitalSignRecord record(RecordVitalSignsCommand command);

    final class RecordVitalSignsCommand {
        public final String patientIdNumber;
        public final String dateIso;        // yyyy-MM-dd
        public final String bloodPressure;  // "120/80"
        public final Double temperatureC;
        public final Integer pulseBpm;
        public final Integer oxygenSaturation;

        public RecordVitalSignsCommand(String patientIdNumber, String dateIso,
                                       String bloodPressure, Double temperatureC,
                                       Integer pulseBpm, Integer oxygenSaturation) {
            this.patientIdNumber = patientIdNumber;
            this.dateIso = dateIso;
            this.bloodPressure = bloodPressure;
            this.temperatureC = temperatureC;
            this.pulseBpm = pulseBpm;
            this.oxygenSaturation = oxygenSaturation;
        }
    }
}
