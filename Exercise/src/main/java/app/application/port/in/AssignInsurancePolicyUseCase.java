/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.application.port.in;

/**
 *
 * @author Diego
 */
import app.domain.model.Patient;
import app.domain.model.InsurancePolicy;

public interface AssignInsurancePolicyUseCase {
    Patient assign(AssignPolicyCommand command);

    final class AssignPolicyCommand {
        public final String patientIdNumber;
        public final InsurancePolicy policy;

        public AssignPolicyCommand(String patientIdNumber, InsurancePolicy policy) {
            this.patientIdNumber = patientIdNumber;
            this.policy = policy;
        }
    }
}
