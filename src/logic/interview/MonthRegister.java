package logic.interview;

import java.util.ArrayList;

public class MonthRegister {
    private String id;
    private int maxDay;
    private ArrayList<Day> days;

    public MonthRegister(String id, int maxDay) {
        setId(id);
        setMaxDay(maxDay);

        // empezar con al menos un dia
        this.days = new ArrayList<Day>();
        createDay();
    }

    /**
     * Crea y añade un nuevo día vacío en el registro
     * 
     * @return el dia creado
     * @throws IllegalStateException si ya se está al límite de dias
     */
    private Day createDay() {
        if (days.size() == maxDay) {
            throw new IllegalStateException("Se llegó al límite de días");
        }

        Day day = new Day(String.valueOf(days.size() + 1), id);
        days.add(day);

        return day;
    }

    /**
     * Crea una entrevista en un día que no tenga el mismo candidato
     * 
     * @throws IllegalStateException si ningún día puede aceptar la cita
     */
    public Interview createInterview(String candidateId, String companyId, String offerId) {
        Interview interview = null;
        int i = 0;

        while (interview == null && i < days.size()) {
            try {
                interview = days.get(i).createInterview(candidateId, companyId, offerId);
            } catch (IllegalStateException e) {
                // ups! No fue posible crear la entrevista en este dia
                // intentar con el siguiente
                i++;
            }
        }

        if (interview == null) {
            // ningún día está disponible
            // iniciar uno nuevo en el registro y colocarlo allí
            Day newDay = createDay();
            return newDay.createInterview(candidateId, companyId, offerId);
        }
        
        return interview;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id != null && id.length() > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
    }

    public int getMaxDay() {
        return maxDay;
    }

    private void setMaxDay(int maxDay) {
        if (maxDay >= 28 && maxDay <= 31) {
            this.maxDay = maxDay;
        } else {
            throw new IllegalArgumentException("El máximo de días del mes debe estar comprendido entre 28 a 31 días.");
        }
    }

    public ArrayList<Day> getDays() {
        return days;
    }
}