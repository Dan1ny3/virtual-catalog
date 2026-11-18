# Catalog Școlar (Java Swing + MySQL)

Aplicație desktop pentru gestiunea elevilor și a notelor dintr-o școală, construită în Java cu interfață Swing și bază de date MySQL accesată prin JDBC. A fost realizată în NetBeans ca proiect personal/universitar, cu scopul de a exersa lucrul cu baze de date relaționale și interfețe grafice.

## Funcționalități

- **Autentificare**: ecran de login care verifică numele și parola din baza de date.
- **Gestiunea elevilor**: adăugare, modificare, căutare și ștergere de elevi, cu afișare tabelară.
- **Note**: introducerea notelor la Fizică, Chimie și Matematică, calculul mediei și atribuirea automată a unui calificativ.
- **Navigare pe module** (`catalog.Catalog`): panouri separate pentru Elevi, Profesori, Cursuri și Note, încărcate din baza de date prin clase model (`Elev`, `Profesor`, `Curs`, `Nota`).

## Tehnologii

| Componentă | Tehnologie |
|---|---|
| Limbaj | Java (proiect NetBeans, configurat pentru JDK 22) |
| Interfață grafică | Swing (NetBeans GUI Builder, `CardLayout`, `JTable`) |
| Bază de date | MySQL, conexiune prin JDBC |
| Driver | MySQL Connector/J 9.0.0 |
| Build | Apache Ant (generat de NetBeans) |

## Structura proiectului

```
src/
├── catalog/   # modelele (Elev, Profesor, Curs, Nota), conexiunea la BD și navigarea pe module
└── scoala/    # ecranele aplicației: Login, meniu, elevi, note
```

## Rulare

### Cerințe

- JDK 22 (sau o versiune compatibilă, ajustând `javac.source` în `nbproject/project.properties`)
- MySQL Server 8+
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) 9.0.0
- NetBeans (recomandat, proiectul este de tip Ant)

### Pași

1. Creează baza de date și tabelele:
   ```sql
   CREATE DATABASE scoala;
   ```
   apoi importă schema din `database/schema.sql`.
2. Deschide proiectul în NetBeans și adaugă `mysql-connector-j-9.0.0.jar` la **Libraries**.
3. Setează datele de conectare la baza de date. În versiunea curentă ele sunt scrise direct în cod (`catalog/DatabaseConnection.java` și în ecranele din `scoala/`): URL `jdbc:mysql://localhost:3306/scoala`, utilizator `root`.
4. Rulează:
   - `scoala.Login` pentru fluxul cu autentificare și meniu;
   - `catalog.Catalog` pentru navigarea pe module (este și clasa principală setată în proiect);
   - `catalog.Verificareconexiune` pentru a testa doar conexiunea la baza de date.

## Limitări cunoscute

Proiectul este în stadiul de prototip și are limitări pe care le-am identificat:

- Datele de conectare la baza de date sunt scrise direct în cod, în loc să fie citite dintr-un fișier de configurare.
- Unele interogări (de exemplu la modificarea și ștergerea elevilor) sunt construite prin concatenare de șiruri, deci sunt vulnerabile la SQL injection; altele folosesc deja `PreparedStatement` cu parametri.
- Parolele sunt stocate și comparate în clar, iar verificarea din ecranul de login citește tabela la fiecare încercare.
- Ecranele `Cheltuieli` și `Profesori` din pachetul `scoala` sunt deocamdată doar interfețe, fără logică de acces la date.
- Logica de acces la date este repetată în mai multe ecrane, în loc să fie centralizată într-un strat separat.

## Posibile îmbunătățiri

- Mutarea configurării într-un fișier `.properties` și a accesului la date într-un strat DAO.
- Folosirea `PreparedStatement` peste tot și stocarea parolelor ca hash (de exemplu BCrypt).
- Migrarea build-ului la Maven sau Gradle.
- Teste unitare pentru calculul mediei și al calificativului.

## Autor

Alexandru Daniel Dobre
