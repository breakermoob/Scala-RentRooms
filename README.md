# RentRooms

## Aplicaciones Empresariales

### Universidad de Antioquia

#### 2019-2

Proyecto de Aplicaciones Empresariales en Scala - Backend para Reserva de Habitaciones

---

[![Codeship Status for breakermoob/Scala-RentRooms](https://app.codeship.com/projects/29bb4250-8767-0138-d1e8-6e38c3c343f5/status?branch=master)](https://app.codeship.com/projects/398707) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/bf1e6701bfe046d38b58701f817dfc3f)](https://www.codacy.com?utm_source=github.com&utm_medium=referral&utm_content=breakermoob/Scala-RentRooms&utm_campaign=Badge_Grade)

## 👥 Integrantes:

- Leon Dario Arango Amaya ~ leon.arango@udea.edu.co

- Nicolas Alberto Henao Avendaño ~ nalberto.henao@udea.edu.co

---

---

## Funcionalidades

- [Search](#search)
- [Details](#details)
- [Booking](#booking)
- [Bookings](#bookings)

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

## Details

> ### GET /Room/:id

#### Descripción

Esta ruta obtiene los detalles de un cuarto por su id.

#### Parámetros entrada - Ejemplo

```
    /Rooms/2
```

#### JSON salida - Ejemplo

```
{
    "id": "2",
    "images": [{
        "url": "https://rentrooms.s3.amazonaws.com/BOG/BOG-1-1.jpg"
    }, {
        "url": "https://rentrooms.s3.amazonaws.com/BOG/BOG-1-2.jpg"
    }],
    "location": {
        "name": "Bogota",
        "code": "BOG",
        "latitude": 4.624335,
        "longitude": -74.063644
    },
    "price": 350000,
    "currency": "COP",
    "agency": {
        "name": "Agencia Scala",
        "id": "42",
        "logo_url": "https://rentrooms.s3.amazonaws.com/Scala.png"
    },
    "property_name": "Hostal Bogota Real",
    "rating": 4.1,
    "services": ["Wifi", "Parqueadero", "Zona para fumadores"]
}
```

## Booking

> ### POST /booking

#### Descripción

Esta ruta permite realizar reservas de un cuarto durante una fecha con los datos del usuario.

#### JSON entrada - Ejemplo

```
{
    "checkin": "2030-06-21",
    "checkout": "2030-06-22",
    "email": "leon.arango@udea.edu.co",
    "name": "Leon Arango",
    "id_room": 1
}
```

#### JSON salida - Ejemplo

```
{
    "id_booking": "24",
    "checkin": "2030-06-21",
    "checkout": "2030-06-22",
    "email": "leon.arango@udea.edu.co",
    "name": "Leon Arango",
    "id_room": 1
}
```

## Bookings

> ### GET /booking/:email

#### Descripción

Esta ruta permite consultar todas las reservas de un usuario por su email.

#### Parámetros entrada - Ejemplo

```
    /booking/leon.arango@udea.edu.co

    Header
    authtoken: TOKEN
```

#### JSON salida - Ejemplo

```
[
    {
        "id_room": "2",
        "thumbnail": "https://rentrooms.s3.amazonaws.com/BOG/BOG-1-tub.jpg",
        "location": {
            "name": "Bogota",
            "code": "BOG",
            "latitude": 4.624335,
            "longitude": -74.063644
        },
        "price": 350000,
        "currency": "COP",
        "agency": {
            "name": "Agencia Scala",
            "id": "42",
            "logo_url": "https://rentrooms.s3.amazonaws.com/Scala.png"
        },
        "property_name": "Hostal Bogota Real",
        "checkin": "2008-01-01",
        "checkout": "2008-01-01",
        "total_price": 350000
    }
]
```
