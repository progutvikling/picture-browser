Picture Browser
===============

Arbeidsoppgave i faget Programvareutvikling på UiS Data Bachelor 2013.
Gruppe 1


Komme i gang
------------


### Hvilke verktøy trengs:


* Java 7
* Eclipse (anbefalt)
* MySQL


### Stegvis beskrivelse:

1. Klon dette prosjektet til en mappe på datamaskinen din.
2. Importer prosjektet i Eclipse. *NB: Ikke huk av for "copy project into workspace"*
2. Installer mysql på localhost, med brukeren `root` uten passord.
   * Opprett databasen `PictureBrowser` og alle tabellene **ved å kjøre alle SQL-scriptene** i mappen `sql-scripts` i rekkefølge etter filnavnene.
   * Viss du må ha brukernavn og passord på databasen, må du redigere `src/dal/admin/StoreFactory.java` med andre connection settings.


### For å kjøre forskjellige deler av programmet:   
   
* Start Admin-panelet med å kjøre `bll.admin.AdminMainController` i Eclipse.
* Alle tester i pakken `test` kan kjøres av JUnit.