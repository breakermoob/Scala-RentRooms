# RentRooms

Proyecto de Aplicaciones Empresariales en Scala - Backend para Reserva de Habitaciones

---

## :busts_in_silhouette: Integrantes:

- Leon Dario Arango Amaya ~ leon.arango@udea.edu.co
- Nicolas Alberto Henao Avendaño ~ nalberto.henao@udea.edu.co

---

---

## Funcionalidades

- [Search](#search)

---

## Search

> ### GET /rooms/search

#### Descripción

Esta ruta obtiene todos los cuartos disponibles en una ciudad, durante una fecha determinada.

#### Parámetros entrada - Ejemplo

```
location=MDE
checkin=2022-06-19
checkout=2022-06-25
```

#### JSON salida - Ejemplo

```
[{
    "id": "1",
    "thumbnail": "https://rentrooms.s3.amazonaws.com/MDE/MDE-1-tub.jpg",
    "location": {
        "name": "Medellin",
        "code": "MDE",
        "latitude": 6.230833,
        "longitude": -75.590553
    },
    "price": 600000,
    "currency": "COP",
    "agency": {
        "name": "Agencia Scala",
        "id": "42",
        "logo_url": "https://rentrooms.s3.amazonaws.com/Scala.png"
    },
    "property_name": "Vendo Casa Grande Santa Monica",
    "rating": 4.5
}]
```

## Ejemplo 2

> ### POST /ruta

#### Descripción

.......... contenido

#### JSON entrada

```
{
    "aaa":"bbb",

}
```

#### JSON salida

```
{
    "aaa":"bbb",

}
```
