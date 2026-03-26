#  Smart Wallet Pro | Full-Stack Java Application

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

To nie jest zwykła tabela wydatków. To kompletna aplikacja **Full-Stack**, która łączy logikę biznesową napisaną w Javie z dynamicznym, interaktywnym front-endem.

##  Co wyróżnia ten projekt?

* **Interaktywna Wizualizacja**: Zintegrowałam bibliotekę **Chart.js**, aby wydatki nie były tylko nudnymi liczbami, ale czytelnym wykresem kołowym.
* **Inteligentne Filtrowanie**: System pozwala na natychmiastowe filtrowanie transakcji po kategoriach (Jedzenie, Rozrywka, Transport) bez przeładowywania strony.
* **Architektura REST**: Komunikacja między Frontem a Backem odbywa się poprzez czyste API (Fetch API).
* **Trwałość Danych**: System automatycznie zapisuje i odczytuje dane z pliku lokalnego, symulując działanie bazy danych.

##  Stack Techniczny

| Warstwa | Technologia |
| :--- | :--- |
| **Backend** | Java 17, Spring Boot 3, Maven |
| **Frontend** | Modern JavaScript (ES6+), HTML5, CSS3 |
| **Analiza danych** | Chart.js (Data Visualization) |

##  Jak to działa? (Logika)

Aplikacja oblicza sumę wydatków w czasie rzeczywistym. Jeśli dodasz nowy wydatek przez formularz, Java odbiera dane, zapisuje je w `expenses.txt`, a JavaScript odświeża wykres, by odzwierciedlał aktualny stan Twojego portfela.

---
*Projekt stworzony w ramach intensywnej nauki programowania obiektowego i webowego.*
