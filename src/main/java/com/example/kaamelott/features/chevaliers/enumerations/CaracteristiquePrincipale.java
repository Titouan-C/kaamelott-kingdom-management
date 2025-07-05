package com.example.kaamelott.features.chevaliers.enumerations;

import lombok.Getter;

@Getter
public enum CaracteristiquePrincipale {
    RONCHON("Ronchon"),
    GOURMAND("Gourmand"),
    LACHE("Lâche"),
    NAIF("Naïf"),
    PINOUF("Pinouf");

    private final String label;

    CaracteristiquePrincipale(String label) {
        this.label = label;
    }

    public static CaracteristiquePrincipale fromLabel(String label) {
        for (CaracteristiquePrincipale caracteristique : values()) {
            if (caracteristique.getLabel().equalsIgnoreCase(label)) {
                return caracteristique;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Caractéristique principale '%s' n'est pas valide. Valeurs possibles : %s",
                label,
                String.join(", ", java.util.Arrays.stream(values()).map(CaracteristiquePrincipale::getLabel).toArray(String[]::new))
        ));
    }

}
