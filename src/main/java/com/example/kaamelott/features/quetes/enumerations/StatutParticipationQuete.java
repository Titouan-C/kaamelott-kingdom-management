package com.example.kaamelott.features.quetes.enumerations;

import lombok.Getter;

import java.util.List;

@Getter
public enum StatutParticipationQuete {
    NON_COMMENCEE("Non commencée"),
    EN_COURS("En cours"),
    TERMINEE("Terminée"),
    ECHOUEE_LAMENTABLEMENT("Échouée lamentablement"),
    ABANDONNEE_PAR_FLEMME("Abandonnée par flemme");

    private final String label;

    StatutParticipationQuete(String label) {
        this.label = label;
    }

    public static StatutParticipationQuete fromLabel(String label) {
        for (StatutParticipationQuete statut : values()) {
            if (statut.getLabel().equalsIgnoreCase(label)) {
                return statut;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Statut de participation '%s' n'est pas valide. Valeurs possibles : %s",
                label,
                String.join(", ", java.util.Arrays.stream(values()).map(StatutParticipationQuete::getLabel).toArray(String[]::new))
        ));
    }

    /**
     * Return a list of statuses that are considered as finished.
     *
     * @return List of finished statuses
     */
    public static List<StatutParticipationQuete> getFinishedStatues() {
        return List.of(
                TERMINEE,
                ECHOUEE_LAMENTABLEMENT,
                ABANDONNEE_PAR_FLEMME
        );
    }

}
