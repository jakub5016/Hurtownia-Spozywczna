# Hurtownia-Spozywczna

# How to run?

Najłatwiejszy sposób to po prostu użycie dockera. Używany wtedy komendy ```docker-compose up --build``` w takim wypadku nic poza dockerem nie powinnno być nam potrzebne.

Jeżeli chcemy uruchomić aplikacje lokalnie należy mieć skonfigurowanego Postgre w ten sposób:
```DB_PORT: 5432, HOST_NAME: db, DB_NAME: Hurtownia-Database, DB_USERNAME: postgres, DB_PASSWORD: admin```

Możemy również skorzystać z kontera zawierającego skonfigurowaną baze danych (nazywa się db) i można uruchomić go niezależnie od backendu. Wystarczy zabić process po uruchomieniu komendy dockera a następnie uruchmić tylko kontener "db" (można zrobić to np. przez docker desktop). Należy wtedy mieć zainstalowaną Jave oraz ustalić zmienne środowiskowe (te same co do konfiguracji postgre). 
W projekcie korzystamy z Javy 17. 

Po uruchomieniu spring zapyta was o nazwe użytkownika i hasło:
* Login: user
* Hasło: 1234

# Common mistakes

Jeżeli kombinuje się z uruchamianiem raz na lokalnym postgre a raz na kontenerze warto zwrócić uwagę na to czy na naszej lokalnej maszynie nie isnieje już process postgre który zaburza działanie naszego dockera. Wtedy warto uruchomić komendę ```sudo systemctl stop postgresql ``` przed uruchomieniem backendu. 

Skrypt ```localBackendInit.sh``` zawiera w sobie wszystkie potrzebne do konfiguracji zmienne środowiskowe (z doświadczenia polecam jednak wkleić te komendy w terminal niż odpalać skrypt bo zależy jak to zrobicie sh czy bash itp.)