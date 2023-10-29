# Skills_1 Backend [Hosted Here](http://66.11.123.158:8080/)
## [Frontend Prototype](https://github.com/WuzzyLV/Skills_1-frontend)
## [Path finding algorithm](https://github.com/WuzzyLV/Skills_1/blob/ce1c840a490af08f4e820a29be69a8fefc26bc12/src/main/java/me/wuzzyxy/skills_1/routes/RouteSystem.java#L70C24-L70C24)
Needs much improvement but works :)

## Mappings
### `/routes`
example:
```json
[
	{
		"name":"Valmieras iela",
		"id":1
	},
	{
		"name":"Ģertrūdes iela",
		"id":2
	},
	{
		"name":"Nometņu iela",
		"id":3
	},
	{
		"name":"Imanta",
		"id":4
	},
	{
		"name":"Kreisā iela",
		"id":5
	},
	{
		"name":"Muižnieka iela",
		"id":6
	},
	{
		"name":"Anniņmuižas bulvāris",
		"id":7
	}
]
```

### `/route/{startID}/{endID}`
example: `/route/1/4`
```json
{
	"lines":[
		{
			"name":"Pirmais autobuss",
			"type":"bus",
			"id":1,
			"stops":[
				{
					"name":"Valmieras iela",
					"id":1
				},
				{
					"name":"Ģertrūdes iela",
					"id":2
				}
			]
		},
		{
			"name":"Otrais tramvajs",
			"type":"tram",
			"id":2,
			"stops":[
				{
					"name":"Ģertrūdes iela",
					"id":2
				},
				{
					"name":"Imanta",
					"id":4
				}
			]
		}
	]
}
```


### Default routes
```json
{
  "lines": [
    {
      "name": "Pirmais autobuss",
      "type": "bus",
      "id": 1,
      "stops": [
        {"name": "Valmieras iela", "id": 1},
        {"name": "Ģertrūdes iela", "id": 2},
        {"name": "Nometņu iela", "id": 3}
      ]
    },
    {
      "name": "Otrais tramvajs",
      "type": "tram",
      "id": 2,
      "stops": [
        {"name": "Imanta", "id": 4},
        {"name": "Ģertrūdes iela", "id": 2},
        {"name": "Kreisā iela", "id": 5}
      ]
    },
    {
      "name": "Trešais tramvajs",
      "type": "tram",
      "id": 3,
      "stops": [
        {"name": "Valmieras iela", "id": 1},
        {"name": "Muižnieka iela", "id": 6},
        {"name": "Anniņmuižas bulvāris", "id": 7}
      ]
    }
  ]
}
```
