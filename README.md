# La Gestion du Royaume de Kaamelott

## Installation

### Prérequis
- Java 19

### Installation de l'application
***TODO***


## Choix techniques du projet

### 1. SGBD : PostgreSQL
**Pourquoi ce choix ?**
- PostgreSQL est un SGBD open-source robuste, très utilisé en entreprise, avec un excellent support des types de
données, des contraintes d'intégrité, et des transactions.
- Il offre de bonnes perfomances et une grande compatibilité avec Spring Data JPA / Hibernate.
**Alternative** : MySQL ou MariaDB, mais PostgreSQL est préféré pour ses fonctionnalités avancées

### 2. Lombok
**Pourquoi ce choix ?**
- Lombok est une bibliothèque Java qui permet de réduire le boilerplate code en générant automatiquement les getters,
setters, constructeurs, etc. Ca rend le code plus lisible et maintenable.
**Alternative** : Faire les getters/setters manuellement, mais cela alourdit le code.

### 3. Validation des entrées (Bean Validation via annotations)
**Pourquoi ce choix ?** 
- Utilisation des annotations de validation (comme @NotNull, @Size, etc.) sur les entités pour garantir l'intégrité
des données en entrée.
**Alternative** : Validation manuelle dans le code, mais cela rend le code moins maintenable et plus sujet aux erreurs.

### 5. Documentation API : Swagger + Documentation manuelle (README.md)
**Pourquoi ce choix ?**
- Swagger permet de générer automatiquement une documentation interactive de l'API REST, facilitant la compréhension.
- La documentation manuelle (README.md) permet de fournir des informations détaillées sur l'installation, la
configuration et les choix techniques du projet.
**Alternative** : Uniquement manuelle, mais cela serait moins interactif et plus difficile à maintenir.

### 6. Gestion des exceptions : BaseException + hierarchie d'exceptions
**Pourquoi ce choix ?**
- Création d'une classe de base `BaseException` pour centraliser la gestion des exceptions et faciliter la maintenance.
- Héritage pour des cas métiers (par exemple ConflictException, NotFoundException) pour une meilleure lisibilité et
compréhension des erreurs.
**Alternative** : Gestion des exceptions sans hiérarchie, mais cela rendrait le code plus verbeux et moins clair.

### 7. Structure du projet : par feature
**Pourquoi ce choix ?**
- Organisation du code par fonctionnalité (feature) plutôt que par couche (controller, service, repository) pour mieux
séparer les responsabilités et faciliter la navigation dans le code.
- Exemple : `src/main/java/com/example/kaamelott/feature/chevaliers/controller/ChevalierController.java`,
`src/main/java/com/example/kaamelott/feature/chevaliers/services/ChevalierService.java`,
`src/main/java/com/example/kaamelott/feature/quetes/repository/QueteRepository.java`, etc.
**Alternative** : Organisation par couche, mais cela rendrait le code plus difficile à naviguer et à maintenir (surtout
si le projet grandit).

### 8. Enums
**Pourquoi ce choix ?**
- Utilisation d'énumérations Java quand le nombre de valeurs est faible et stable (meilleur performance, simplicité).
- Possibilité d'utiliser des tables en base si besoin de rendre dynamique.
**Alternative** : Utiliser des constantes, mais cela rendrait le code moins lisible et plus sujet aux erreurs.

### 9. Suppression soft
**Pourquoi ce choix ?**
- On ne supprime jamais vraiment une donnée pour garder l'historique, mais on la marque comme inactive (grâce à un champ
`deleted_at` nullable).
**Alternative** : Suppression physique, mais cela rendrait impossible la récupération des données supprimées.

### 10. Pagination
**Pourquoi ce choix ?**
- Utilisation de la pagination pour les listes d'entités (par exemple, les quêtes, les chevaliers) pour éviter de
charger trop de données en mémoire et améliorer les performances (même avec peu de données).
**Alternative** : Pas de pagination, mais cela pourrait entraîner des problèmes de performance et de mémoire si le
nombre d'entités augmente dans le futur.

### 11. Gestion des dépendances : Gradle
**Pourquoi ce choix ?**
- C'est un outil de build moderne et performant.
- Sa syntaxe est plus lisible que celle de Maven, et il permet une meilleure gestion des dépendances.
**Alternative** : Maven (solution historique et stable), mais Gradle est préféré pour sa flexibilité et sa rapidité.