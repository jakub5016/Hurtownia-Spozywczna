#!/bin/bash

#Creatin users
curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "admin",
  "firstName": "Janusz",
  "secondName": "Kiełbasa",
  "password": "string",
  "type": "ADMIN",
  "address": "Aleja Hogwartu 1, Hogsmeade"
}'

curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "user2",
  "firstName": "Patryk",
  "secondName": "Woziński",
  "password": "string",
  "type": "CLIENT",
  "address": "Melina 4, Mordor"
}'

curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "user3",
  "firstName": "Karyna",
  "secondName": "Krwiak",
  "password": "string",
  "type": "CLIENT",
  "address": "Ciemna Ulica 5, Gotham"
}'

curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "user4",
  "firstName": "Brajan",
  "secondName": "Krwawy",
  "password": "string",
  "type": "CLIENT",
  "address": "Aleja Przemocy 6, Vice City"
}'

curl -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "user",
  "firstName": "Sergiusz",
  "secondName": "Górski",
  "password": "string",
  "type": "CLIENT",
  "address": "Aleja Willy Wonki 3, Madryt, Hiszpania"
}'

curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "emp",
  "firstName": "Grażyna",
  "secondName": "Pączek",
  "password": "string",
  "type": "EMPLOYEE",
  "address": "Radom, po prostu Radom"
}'

#Addin products
curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Marchew",
  "category": "WARZYWA_I_OWOCE",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Chleb",
  "category": "PIECZYWO",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Ser",
  "category": "NABIAL",
  "quantity": 75
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Kiełbasa",
  "category": "MIESO_I_WEDLINY",
  "quantity": 40
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 15,
  "name": "Łosoś",
  "category": "RYBY_I_OWOCE_MORZA",
  "quantity": 30
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 8,
  "name": "Mrożona pizza",
  "category": "MROZONKI",
  "quantity": 60
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Puszka fasoli",
  "category": "KONSERWY",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Ryż",
  "category": "PRODUKTY_SYPKIE",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Pieprz",
  "category": "PRZYPRAWY_I_ZIOLA",
  "quantity": 200
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Czekolada",
  "category": "SLODYCZE_I_PRZEKASKI",
  "quantity": 150
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 1,
  "name": "Woda mineralna",
  "category": "NAPOJE_BEZALKOHOLOWE",
  "quantity": 250
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 20,
  "name": "Wino",
  "category": "NAPOJE_ALKOHOLOWE",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 7,
  "name": "Oliwa z oliwek",
  "category": "OLEJE_I_TLUSZCZE",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 12,
  "name": "Mleko sojowe",
  "category": "PRODUKTY_DIETETYCZNE_I_EKOLOGICZNE",
  "quantity": 60
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 14,
  "name": "Makaron bezglutenowy",
  "category": "PRODUKTY_BEZGLUTENOWE",
  "quantity": 90
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Kawa",
  "category": "KAWA_HERBATA_I_KAKAO",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Ketchup",
  "category": "SOSY_I_DRESSINGI",
  "quantity": 110
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Dżem truskawkowy",
  "category": "PRZETWORY_OWOCOWE_I_WARZYWNE",
  "quantity": 130
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Wasa chleb chrupki",
  "category": "PIECZYWO_CHRUPKIE_I_SUCHARKI",
  "quantity": 140
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Zupa instant",
  "category": "ZUPY_INSTANT_I_DANIA_GOTOWE",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Musli",
  "category": "PLATKI_SNIADANIOWE_I_MUSLI",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Deserek owocowy",
  "category": "PRODUKTY_DLA_DZIECI",
  "quantity": 90
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Baton energetyczny",
  "category": "PRZEKASKI_NA_WYNOS",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Ciastka",
  "category": "ARTYKULY_CUKIERNICZE",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Kanapki",
  "category": "ARTYKULY_SNIADANIOWE",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Śruby",
  "category": "SRUBY",
  "quantity": 200
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 1,
  "name": "Nakrętki",
  "category": "NAKRETKI",
  "quantity": 300
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Podkładki",
  "category": "PODKLADKI",
  "quantity": 150
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Wkręty",
  "category": "WKRETY",
  "quantity": 250
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Klej do drewna",
  "category": "KLEJE_I_SILIKONY",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Taśma malarska",
  "category": "TASMY_BUDOWLANE",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 8,
  "name": "Młotek",
  "category": "NARZEDZIA_RECZNE",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 50,
  "name": "Wiertarka elektryczna",
  "category": "NARZEDZIA_ELEKTRYCZNE",
  "quantity": 30
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 20,
  "name": "Drabina aluminiowa",
  "category": "DRABINY_I_RUSZTOWANIA",
  "quantity": 40
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 7,
  "name": "Maty izolacyjne",
  "category": "MATERIALY_IZOLACYJNE",
  "quantity": 60
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Uszczelka drzwiowa",
  "category": "USZCZELKI_I_PROFILE",
  "quantity": 150
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Farba lateksowa",
  "category": "FARBY_I_LAKIERY",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Pędzel malarski",
  "category": "WALKI_I_PEDZLE_MALARSKIE",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Żarówka LED",
  "category": "OSWIETLENIE",
  "quantity": 200
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Gniazdo elektryczne",
  "category": "AKCESORIA_ELEKTRYCZNE",
  "quantity": 180
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Przedłużacz",
  "category": "PRZEDLUZACZE_I_LISTWY_ZASILAJACE",
  "quantity": 90
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 8,
  "name": "Rura PVC",
  "category": "RURY_I_ZLACZKI_HYDRAULICZNE",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 15,
  "name": "Bateria łazienkowa",
  "category": "KRANY_I_BATERIE_LAZIENKOWE",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 30,
  "name": "Wentylator sufitowy",
  "category": "WENTYLATORY_I_KLIMATYZATORY",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 25,
  "name": "Panele podłogowe",
  "category": "PANELE_PODLOGOWE_I_LISTWY_PRZYPODLOGOWE",
  "quantity": 60
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Gładź gipsowa",
  "category": "MATERIALY_WYKONCZENIOWE",
  "quantity": 100
}'

#Add orders 
curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/order' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "clientId": 2,
  "orderedProducts": [
    {
      "productId": 1,
      "quantity": 3
    },
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/order' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "clientId": 2,
  "orderedProducts": [
    {
      "productId": 3,
      "quantity": 8
    }
  ]
}'

for i in {1..10}
do
    curl -b /tmp/cookies.txt -X 'POST' \
      'http://localhost:8080/order' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
      "clientId": '$((RANDOM % 5 + 1))',
      "orderedProducts": [
        {
          "productId": '$((RANDOM % 50 + 1))',
          "quantity": '$((RANDOM % 10 + 1))'
        },
        {
          "productId": '$((RANDOM % 50 + 1))',
          "quantity": '$((RANDOM % 10 + 1))'
        }
      ]
    }'
done