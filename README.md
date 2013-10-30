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
3. Installer mysql på localhost, med brukeren `root` uten passord.
   * Installer alle migrations med å skrive:

     ```bash
	 mvn compile
	 mvn flyway:migrate
	 ```

	 (Men du må ha installert maven for kommandolinja. På mac kan du
     gjøre dette med `homebrew`. Installer først homebrew med:
	 `ruby -e "$(curl -fsSL https://raw.github.com/mxcl/homebrew/go)"`
	 og installer deretter maven med `brew maven`.)

4. Hver gang det har blitt lagt til nye SQL-filer i migrations-mappa
   må du kjøre steg 3 igjen.


### For å kjøre forskjellige deler av programmet:   
   
* Start Admin-panelet med å kjøre `bll.admin.AdminMainController` i Eclipse.
* Alle tester i pakken `test` kan kjøres av JUnit.
