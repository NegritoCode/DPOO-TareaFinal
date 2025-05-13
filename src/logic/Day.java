package logic;

import java.util.ArrayList;

public class Day {
    private String id;
    private String monthId;
    private ArrayList<Interview> interviews;

    public Day(String id, String monthId) {
        setId(id);
        setMonthId(monthId);

        this.interviews = new ArrayList<Interview>();
    }

    /**
     * Añade una entrevista si no existe el mismo candidato
     */
    public void createInterview(String candidateCid, String companyId, String offerId) {
        if (getInterview(candidateCid) == null) {
            interviews.add(new Interview(monthId, id, candidateCid, companyId, offerId));
        } else {
            throw new IllegalStateException("Ya existe una entrevista asignada en este día para " + candidateCid);
        }

    }

    /**
     * Obtener la entrevista segun el cid del candidato
     * Retorna null si no se encuentra
     */
    public Interview getInterview(String candidateCid) {
        Interview founded = null;
        int i = 0;

        while (founded == null && i < interviews.size()) {
            Interview interview = interviews.get(i);
            if (interview.getCandidateCid() == candidateCid) {
                // se encontró la entervista!
                founded = interview;
            }
            i++;
        }

        return founded;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

	private void setId(String id) {
		this.id = id;
	}

	public String getMonthId() {
		return monthId;
	}

	private void setMonthId(String monthId) {
		this.monthId = monthId;
	}

    public ArrayList<Interview> getInterviews() {
        return interviews;
    }
}
