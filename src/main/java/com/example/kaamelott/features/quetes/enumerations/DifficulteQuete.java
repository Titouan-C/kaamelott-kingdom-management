package com.example.kaamelott.features.quetes.enumerations;

import lombok.Getter;

@Getter
public enum DifficulteQuete {
    FACILE("Facile"),
    MOYENNE("Moyenne"),
    ABERRANTE("Aberrante");

    private final String label;

    DifficulteQuete(String label) {
        this.label = label;
    }

    public static DifficulteQuete fromLabel(String label) {
        for (DifficulteQuete difficulte : values()) {
            if (difficulte.getLabel().equalsIgnoreCase(label)) {
                return difficulte;
            }
        }
        throw new IllegalArgumentException("No DifficulteQuete found for label: " + label);
    }

}
