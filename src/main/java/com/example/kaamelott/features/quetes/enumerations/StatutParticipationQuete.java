package com.example.kaamelott.features.quetes.enumerations;

import lombok.Getter;

@Getter
public enum StatutParticipationQuete {
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
        throw new IllegalArgumentException("No StatutParticipationQuete found for label: " + label);
    }

}
