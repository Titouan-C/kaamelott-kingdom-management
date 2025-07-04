package com.example.kaamelott.features.quetes.enumerations;

import lombok.Getter;

@Getter
public enum ParticipationQueteRole {
    CHEF_EXPEDITION("Chef d'Expédition"),
    ACOLYTE("Acolyte"),
    RESERVE("Réserve");

    private final String label;

    ParticipationQueteRole(String label) {
        this.label = label;
    }

    public static ParticipationQueteRole fromLabel(String label) {
        for (ParticipationQueteRole role : values()) {
            if (role.getLabel().equalsIgnoreCase(label)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No ParticipationQueteRole found for label: " + label);
    }

}
