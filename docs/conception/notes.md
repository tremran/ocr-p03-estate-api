# ESTATE

## routes

Les routes de l'api commencent par http://localhost:3001/api

### routes non sécurisées

Ses routes sont publiques, donc accessibles tout le temps

| URL | Méthode | Description | Body (JSON) | Status | Exemple de réponse | Notes |
| --- | --- | --- | --- | --- | --- | --- |
| /auth/register | POST | register a user | `{"name":"n", "email":"e@me.fr", "password":"p"}` | 200 | `{"token": "jwt"}` | all fields are required OR get error 400|
| /auth/login | POST | login a user | `{"email":"e@me.fr", "password":"p"}` | 200 | `{"token": "jwt"}` | user exists in DB or get error 401 |

### routes sécurisées

ces routes nécessitent d'envoyer le token obtenu lors de la connexion sur la route `auth/register`
Si le token n'est pas fourni, l'utilisateur obtient une réponse http 401 - Unauthorized

| URL | Méthode | Description | Body (JSON) | Status | Exemple de réponse | Notes |
| --- | --- | --- | --- | --- | --- | --- |
| /auth/me | GET | gets logged user information | N/A | 200 | `{"id": 1, "name":"n", "email":"e@me.fr", "created_at":"2026/05/11", "updated_at":"2026/05/11"}` |  |
| /user/{:user_id} | GET | gets informations about the user with id `user_id` | N/A | 200 | `{"id": 2, "name":"n", "email":"e@me.fr", "created_at":"2026/05/11", "updated_at":"2026/05/11"}` |  |
| /messages | POST | sends a message about a rental | `{"user_id": 1, "message":"n", "rental_id":"1"}` | 200 | `{"message": "Message send with success"}` | all fields are required or get error 400 |
| /rentals | GET | get all rentals | N/A | 200 | [see description](#rentals) |  |
| /rentals | POST | inserts a rental in DB | [see description](#post-rentals)  | 200 | `{"message": "Rental created !"}` |  |
| /rentals/{:rental_id} | GET | get informations about rental with id `rental_id` | N/A | 200 | [see description](#rentalsid) |  |
| /rentals/{:rental_id} | PUT | replace informations about rental with id `rental_id` | [see description](#post-rentals) | 200 | `{"message": "Rental updated !"}` |  |

### responses

#### /rentals

```json
{ 
  "rentals": [
    {
        "id": 1,
        "name": "test house 1",
        "surface": 432,
        "price": 300,
        "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
        "description": "Lorem ipsum ",
        "owner_id": 1,
        "created_at": "2012/12/02",
        "updated_at": "2014/12/02"  
    },
    {...},
    ...
  ]
}
```

#### /rentals/:id

```json
{
	"id": 1,
	"name": "dream house",
	"surface": 24,
	"price": 30,
	"picture": ["https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg"],
	"description": "Lorem ipsum ",
	"owner_id": 1,
	"created_at": "2012/12/02",
	"updated_at": "2014/12/02"  
}
```

#### POST /rentals

```json
{ 
    "name": "test house 1",
    "surface": 432,
    "price": 300,
    "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
    "description": "Lorem ipsum ",
    "owner_id": 1
}
```

