# ZamowBook - Pierwsza rozszerzona aplikacja napisana w Spring
<br>
11-modułowy program, w trakcie którego pokazywane są krok po kroku tajniki najpopularniejszego frameworka w świecie Javy na podstawie realnej aplikacji, którą na koniec wdrażamy na produkcję.
(Niestety przez wzgląd, że Heroku przeszedl na płatną subskrybcje aplikacja nie działa już na wdrożonym serwerze)
<br>
<br>
<ul> W ramach programu realizowane były poniższe tematy
<li><strong> Fundamenty Springa </strong>
<li><strong> Architektura </strong>
<li><strong> Spring REST API </strong>
<li><strong> JPA & Hibernate </strong>
<li><strong> Testowanie </strong>
<li><strong> UI </strong>
<li><strong> Security </strong>
<li><strong> Wdrażanie na produkcję </strong>
</ul>
<br>
Dla bazy danych zastosowałem kontenery dockerowe, na których umieściłem odpowiedni obraz po skonfigurowaniu odpowiedniego pliku. By urumić aplikacje na początku trzeba zastosować poniższe komendy by zainicjalizować baze danych - korzystam z PostgreSQL

```command
cd docker
docker-compose up
```
<br>
Na ten moment SDK 15 - w przyszłości wykorzystam i zrefaktoryzuje do najnowszej wersji jaka będzie dostępna.
<hr>

### Aplikacja napisana pod kierownictwem - Szkoła Springa
https://www.sztukakodu.pl/spring/