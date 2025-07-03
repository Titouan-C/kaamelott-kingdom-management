-- Suprression des tables existantes si elles existent

DROP TABLE IF EXISTS participation_quetes CASCADE;
DROP TABLE IF EXISTS quetes CASCADE;
DROP TABLE IF EXISTS chevaliers CASCADE;
DROP TABLE IF EXISTS chevalier_titres CASCADE;


-- Création des tables

CREATE TABLE chevalier_titres (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titre VARCHAR(50) NOT NULL
);

CREATE TABLE chevaliers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom VARCHAR(100) NOT NULL,
    id_titre UUID,
    caracteristique_principale VARCHAR(50),
    niveau_bravoure INT NOT NULL,
    deleted_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT fk__chevaliers__titres FOREIGN KEY (id_titre) REFERENCES chevalier_titres(id) ON DELETE SET NULL
);

CREATE TABLE quetes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom_quete VARCHAR(255) NOT NULL,
    description TEXT,
    difficulte VARCHAR(20) NOT NULL,
    date_assignation DATE NOT NULL,
    date_echeance DATE NOT NULL,
    deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE participation_quetes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_chevalier UUID,
    id_quete UUID,
    role VARCHAR(20),
    statut_participation VARCHAR(30),
    commentaire_roi VARCHAR(255),
    UNIQUE(id_chevalier, id_quete),
    CONSTRAINT fk__participation_quetes__chevaliers FOREIGN KEY (id_chevalier) REFERENCES chevaliers(id) ON DELETE CASCADE,
    CONSTRAINT fk__participation_quetes__quetes FOREIGN KEY (id_quete) REFERENCES quetes(id) ON DELETE CASCADE
);

-- Insertion de données initiales dans les tables

INSERT INTO chevalier_titres (id, titre) VALUES
('f8c0b1e2-3d4e-4f5a-8b6c-7d8e9f0a1b2c', 'Sire'),
('d2e3f4a5-6b7c-8d9e-0f1a-2b3c4d5e6f7f', 'Chevalier'),
('a1b2c3d4-e5f6-7a8b-9c0d-e1f2c3d4e5f6', 'Comte');

INSERT INTO chevaliers (id, nom, id_titre, caracteristique_principale, niveau_bravoure) VALUES
('a7ada09d-fd3d-47e9-a413-6885e19f2bd1', 'Léodogan', 'f8c0b1e2-3d4e-4f5a-8b6c-7d8e9f0a1b2c', 'RONCHON', 5),
('f835ae74-0e0f-4a52-916a-bf817026b0c5', 'Perceval', 'd2e3f4a5-6b7c-8d9e-0f1a-2b3c4d5e6f7f', 'GOURMAND', 4),
('9b894c16-3a08-4f9b-87d1-16af482042ec', 'Karadoc', 'a1b2c3d4-e5f6-7a8b-9c0d-e1f2c3d4e5f6', 'LACHE', 3);

INSERT INTO quetes (id, nom_quete, description, difficulte, date_assignation, date_echeance) VALUES
('732e94c2-9e57-4fa1-96ec-3eb845dd8cdd', 'La quête du Graal', 'Trouver le Saint Graal pour le roi Arthur.', 'ABERANTE', '2025-01-01', '2025-12-31'),
('8af9f6a1-481d-4477-ab15-18d15a5f1c37', 'Ramener du poisson frais', 'Aller pêcher du poisson frais pour le banquet du roi.', 'FACILE', '2025-02-01', '2025-11-30'),
('795a1eef-5e5d-4d6c-adc0-89d3785af3a9', 'Comprendre un truc', 'Aider Perceval à comprendre un truc compliqué.', 'DIFFICILE', '2025-03-01', '2025-10-31');

INSERT INTO participation_quetes (id_chevalier, id_quete, role, statut_participation, commentaire_roi) VALUES
('a7ada09d-fd3d-47e9-a413-6885e19f2bd1', '732e94c2-9e57-4fa1-96ec-3eb845dd8cdd', 'Chef de quête', 'EN_COURS', 'Bonne chance, Léodogan !'),
('f835ae74-0e0f-4a52-916a-bf817026b0c5', '8af9f6a1-481d-4477-ab15-18d15a5f1c37', 'Pêcheur', 'TERMINEE', 'Perceval, tu dois pêcher du poisson frais !'),
('9b894c16-3a08-4f9b-87d1-16af482042ec', '795a1eef-5e5d-4d6c-adc0-89d3785af3a9', 'Conseiller', 'ECHOUEE_LAMENTABLEMENT', 'Karadoc, tu as été d’une grande aide.');