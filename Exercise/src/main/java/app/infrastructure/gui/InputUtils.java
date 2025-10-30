package app.infrastructure.gui;

import javax.swing.*;

/**
 * Helper para manejar entradas de usuario y cancelaciones.
 */
public class InputUtils {

    /**
     * Pide una entrada obligatoria, repitiendo hasta recibir un valor no vacío
     * o cancelar la operación.
     */
    public static String askRequired(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la operación?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                    return null;
                } else continue;
            }
            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.");
                continue;
            }
            return input.trim();
        }
    }

    /**
     * Pide una entrada opcional; retorna null si el usuario confirma cancelar.
     */
    public static String askOptional(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la operación?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                    return null;
                } else continue;
            }
            return input.trim();
        }
    }

    /**
     * Confirma una acción con el usuario (Sí / No).
     */
    public static boolean confirmAction(String message) {
        int resp = JOptionPane.showConfirmDialog(null, message, "Confirmar", JOptionPane.YES_NO_OPTION);
        return resp == JOptionPane.YES_OPTION;
    }

    /**
     * Pide un número entero obligatorio al usuario.
     */
    public static int askInt(String message) {
        while (true) {
            String input = askRequired(message);
            if (input == null) return -1; // cancelado
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            }
        }
    }
}
