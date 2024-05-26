#!/bin/bash

#Creatin users
curl -c /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "admin",
  "firstName": "string",
  "secondName": "string",
  "password": "string",
  "type": "ADMIN",
  "address": "string"
}'

curl -X 'POST' \
  'http://localhost:8080/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userName": "user",
  "firstName": "string",
  "secondName": "string",
  "password": "string",
  "type": "CLIENT",
  "address": "string"
}'


#Addin products
curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Marchew",
  "category": "Warzywa i owoce",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Chleb",
  "category": "Pieczywo",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 15,
  "name": "Ser żółty",
  "category": "Nabiał",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 20,
  "name": "Szynka",
  "category": "Mięso i wędliny",
  "quantity": 60
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 25,
  "name": "Łosoś",
  "category": "Ryby i owoce morza",
  "quantity": 40
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 8,
  "name": "Zielony groszek mrożony",
  "category": "Mrożonki",
  "quantity": 90
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 12,
  "name": "Fasolka konserwowa",
  "category": "Konserwy",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Ryż biały",
  "category": "Produkty sypkie",
  "quantity": 150
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Pieprz czarny mielony",
  "category": "Przyprawy i zioła",
  "quantity": 200
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Czekolada mleczna",
  "category": "Słodycze i przekąski",
  "quantity": 120
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 2,
  "name": "Woda mineralna",
  "category": "Napoje bezalkoholowe",
  "quantity": 300
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 50,
  "name": "Wino czerwone",
  "category": "Napoje alkoholowe",
  "quantity": 50
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 7,
  "name": "Oliwa z oliwek",
  "category": "Oleje i tłuszcze",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 12,
  "name": "Migdały bezglutenowe",
  "category": "Produkty bezglutenowe",
  "quantity": 70
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 4,
  "name": "Herbata zielona",
  "category": "Kawa, herbata i kakao",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 5,
  "name": "Ketchup",
  "category": "Sosy i dressingi",
  "quantity": 130
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 7,
  "name": "Ogórki kiszone",
  "category": "Przetwory owocowe i warzywne",
  "quantity": 90
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 6,
  "name": "Chleb chrupki",
  "category": "Pieczywo chrupkie i sucharki",
  "quantity": 110
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 15,
  "name": "Zupa instant",
  "category": "Zupy instant i dania gotowe",
  "quantity": 140
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 8,
  "name": "Musli owocowe",
  "category": "Płatki śniadaniowe i musli",
  "quantity": 160
}'
curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.10,
  "name": "Śruba M6",
  "category": "Śruby",
  "quantity": 500
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.05,
  "name": "Nakrętka M6",
  "category": "Nakrętki",
  "quantity": 1000
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.02,
  "name": "Podkładka M6",
  "category": "Podkładki",
  "quantity": 1500
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.15,
  "name": "Krzyżak do glazury 2mm",
  "category": "Krzyżaki do glazury",
  "quantity": 800
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.12,
  "name": "Wkręt do drewna 3.5x35",
  "category": "Wkręty",
  "quantity": 600
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.08,
  "name": "Kołek rozporowy 6x30",
  "category": "Kołki rozporowe",
  "quantity": 900
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.05,
  "name": "Gwóźdź 3.5x40",
  "category": "Gwoździe",
  "quantity": 1000
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.10,
  "name": "Nit 4x10",
  "category": "Nity",
  "quantity": 700
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 3,
  "name": "Klej montażowy",
  "category": "Kleje i silikony",
  "quantity": 150
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 1.5,
  "name": "Taśma malarska 48mm",
  "category": "Taśmy budowlane",
  "quantity": 200
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 10,
  "name": "Młotek 500g",
  "category": "Narzędzia ręczne",
  "quantity": 80
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 100,
  "name": "Wiertarka udarowa",
  "category": "Narzędzia elektryczne",
  "quantity": 30
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 50,
  "name": "Drabina aluminiowa 3m",
  "category": "Drabiny i rusztowania",
  "quantity": 20
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 7,
  "name": "Pianka montażowa 750ml",
  "category": "Materiały izolacyjne",
  "quantity": 100
}'

curl -b /tmp/cookies.txt -X 'POST' \
  'http://localhost:8080/product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0.50,
  "name": "Uszczelka gumowa 50mm",
  "category": "Uszczelki i profile",
  "quantity": 500
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