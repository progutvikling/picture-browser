Picture Browser
===============

[![Build Status](https://asjohannsson.ci.cloudbees.com/buildStatus/icon?job=Prosjekt)](https://asjohannsson.ci.cloudbees.com/job/Prosjekt/)

Arbeidsoppgave i faget Programvareutvikling på UiS Data Bachelor 2013.
Gruppe 1


Komme i gang
------------


### Hvilke verktøy trengs:

* Java 7 (!)
* Eclipse (anbefalt)
* Maven
* MySQL


### Installere disse verktøyene på mac:

1. Installer Homebrew viss du ikke har det: `ruby -e "$(curl -fsSL https://raw.github.com/mxcl/homebrew/go)"`
2. Installer maven: `brew install maven`
3. Verifiser at Maven kjører med JRE 7: `mvn -version`, se at Java version sier 1.7...
   * Hvis ikke: installer JRE 7: (http://www.java.com/en/download/faq/java_mac.xml)
   * Nå må du antagelig finne ut hvor denne ligger. Mest sannsynlig i
     `/Library/Java/JavaVirtualMachines/jdk1.7[...]`
   * Kjør så `export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home"`
4. Installer mysql: `brew install mysql`
5. Start mysql: `mysql.server start`
6. Gå inn i mysql og lag databasen:
   ```bash
   mysql -uroot
   [...]
   CREATE DATABASE PictureBrowser;
   ```


### Kjøre prosjektet

Dette krever at alle verktøy er installert, og at du har en mysql
database med navn PictureBrowser kjørende på localhost med brukeren
root og uten passord.

Dersom du har et annet oppsett av MySQL-serveren må du sette MYSQL_URL
til en annen JDBC-url. For eksempel: ```bash export
MYSQL_URL=jdbc:mysql://username:password@localhost/PictureBrowser ```

1. Klon dette prosjektet til en mappe på datamaskinen din. (`git clone ...`)
2. (Du kan nå importere prosjektet i Eclipse som et maven-prosjekt)
3. Sett opp databasen med å kjøre alle database-migrasjoner:

    ```bash
	mvn process-resources
	mvn flyway:migrate
	```

4. Hver gang det har blitt lagt til nye SQL-filer i migrations-mappa
   må du kjøre steg 3 igjen.

Du kan nå kjøre ulike deler av programmet i eclipse. Eller du kan bruke maven og pakke prosjektet i kjørbare jars:


### Lage kjørbare JAR-filer

```bash
mvn package
```

(dette vil også kjøre gjennom alle testene, inkludert
integrasjonstestene. Du kan unngå det med å legge til
`-Dmaven.test.skip=true`)

Du har nå laget JAR-filer i `target/`. Du har 4 jar-filer der:
* fetcher (kjører i bakgrunnen, start i kommandolinja om du vil se output)
* admin
* server (kjører i bakgrunnen, start i kommandolinja om du vil se output)
* client (avhengig av at server kjører)
Disse er også avhengige av alle jar-filene i `target/lib` for å kjøre.


### For å kjøre forskjellige deler av programmet:
   
* Start Admin-panelet med å kjøre `admin.MainController` i Eclipse.
* Alle tester i pakken `test` kan kjøres av JUnit.
