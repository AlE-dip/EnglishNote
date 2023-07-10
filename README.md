# English Note API

Sử dụng **Spring boot**, **JPA**, **Spring Security** + **JWT** để tạo API

API sử dụng cho việc lưu, tìm kiếm và chỉnh sửa các từ tiếng Anh 

Data đã được thêm mặc định từ file **english.json** có sẵn trong **resource**

Link [@Postman](https://planetary-desert-10407.postman.co/workspace/English~ddf472ce-d51f-4227-a4f7-a3053642cd15/collection/14981914-85780aa1-e552-4e12-bb85-f17ddae5643e?action=share&creator=14981914) để test API.

![image](https://github.com/AlE-dip/EnglishNote/assets/71812422/cb10088b-3268-4347-8e15-64d35a54b69c)


## Authorize
Đăng nhập và lấy **access token** phục vụ cho các request khác, **username**/**password** mặc định là: **admin**/**admin**

**Request**
```sh
curl --location 'http://localhost:8080/authorize' \
--header 'Content-Type: application/json' \
--data '{
    "username": "admin",
    "password": "admin"
}'
```
**Response**
```json
{
    "username": "admin",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg4OTgxMzE5LCJleHAiOjE2ODk4NDUzMTl9.pzbYIlFNBHtUMQ9tK2bFFaUUX8ETKSdhOj6MwPERSDW8ZxHAaPXNp5xMxrfAK1O8q8qNWF83nArVkJscIAxuhg"
}
```
## Word
Thêm và chỉnh sửa các từ tiếng Anh

<details>
<summary>Query Word: Lấy danh sách các từ</summary>

**Request**
```sh
curl --location 'http://localhost:8080/word/query?page=1&size=2&sortBy=relationWords&sortType=DESC' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```json
[
    {
        "id": 814,
        "english": "volunteer",
        "date": "03-04-2023",
        "notification": 0,
        "auto": 1,
        "game": 1,
        "forget": 0,
        "means": [
            {
                "id": 363,
                "meanWord": "tình nguyện viên",
                "type": {
                    "id": 6,
                    "name": "n"
                }
            }
        ],
        "tags": [
            {
                "id": 9,
                "name": "office work"
            }
        ],
        "relationWords": [
            {
                "id": 3,
                "relationType": "RELATED",
                "wordRelationId": 815,
                "wordRelation": "voluntary"
            },
            {
                "id": 912,
                "relationType": "RELATED",
                "wordRelationId": 816,
                "wordRelation": "voluntarily"
            }
        ]
    },
    {
        "id": 816,
        "english": "voluntarily",
        "date": "03-04-2023",
        "notification": 0,
        "auto": 1,
        "game": 1,
        "forget": 0,
        "means": [
            {
                "id": 360,
                "meanWord": "tự nguyện, tình nguyện",
                "type": {
                    "id": 1,
                    "name": "adv"
                }
            }
        ],
        "tags": [
            {
                "id": 9,
                "name": "office work"
            }
        ],
        "relationWords": [
            {
                "id": 7,
                "relationType": "ANTONYM",
                "wordRelationId": 813,
                "wordRelation": "grudgingly"
            },
            {
                "id": 904,
                "relationType": "RELATED",
                "wordRelationId": 815,
                "wordRelation": "voluntary"
            },
            {
                "id": 912,
                "relationType": "RELATED",
                "wordRelationId": 814,
                "wordRelation": "volunteer"
            }
        ]
    }
]
```
</details>

<details>
<summary>Search Word: Tìm kiếm các từ (theo từ/ theo nghĩa của từ)</summary>

**Request**
```sh
curl --location 'http://localhost:8080/word/search?value=voluntarily&page=1&size=100&sortBy=id&sortType=ASC' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```json
[
    {
        "id": 816,
        "english": "voluntarily",
        "date": "03-04-2023",
        "notification": 0,
        "auto": 1,
        "game": 1,
        "forget": 0,
        "means": [
            {
                "id": 360,
                "meanWord": "tự nguyện, tình nguyện",
                "type": {
                    "id": 1,
                    "name": "adv"
                }
            }
        ],
        "tags": [
            {
                "id": 9,
                "name": "office work"
            }
        ],
        "relationWords": [
            {
                "id": 7,
                "relationType": "ANTONYM",
                "wordRelationId": 813,
                "wordRelation": "grudgingly"
            },
            {
                "id": 904,
                "relationType": "RELATED",
                "wordRelationId": 815,
                "wordRelation": "voluntary"
            },
            {
                "id": 912,
                "relationType": "RELATED",
                "wordRelationId": 814,
                "wordRelation": "volunteer"
            }
        ]
    }
]
```
</details>

<details>
<summary>Insert Word: Thêm từ mới</summary>

**Request**
```sh
curl --location 'http://localhost:8080/word' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <ACCESS_TOKEN> \
--data '{
    "english": "qwerty",
    "means": [
        {
            "meanWord": "qwe",
            "typeId": 3
        }
    ],
    "tags": [
        4
    ],
    "relationWords": [
        {
            "relationType": "RELATED",
            "wordRelationId": 815
        }
    ]
}'
```
**Response**
```json
{
    "id": 1169,
    "english": "qwerty",
    "date": "10-07-2023",
    "notification": 0,
    "auto": 1,
    "game": 1,
    "forget": 0,
    "means": [
        {
            "id": 1208,
            "meanWord": "qwe",
            "type": {
                "id": 3,
                "name": "adj"
            }
        }
    ],
    "tags": [
        {
            "id": 4,
            "name": "leisure time, community"
        }
    ],
    "relationWords": [
        {
            "id": 916,
            "relationType": "RELATED",
            "wordRelationId": 815,
            "wordRelation": "voluntary"
        }
    ]
}
```
</details>

<details>
<summary>Update Word: Chỉnh sửa từ</summary>

**Request**
```sh
curl --location --request PUT 'http://localhost:8080/word/1169' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <ACCESS_TOKEN> \
--data '{
    "english": "qwerty",
    "means": [
        {
            "meanWord": "qwe",
            "typeId": 3
        }
    ],
    "tags": [
        4
    ],
    "relationWords": [
        {
            "relationType": "RELATED",
            "wordRelationId": 815
        }
    ]
}'
```
**Response**
```json
{
    "id": 1169,
    "english": "qwerty",
    "date": "10-07-2023",
    "notification": 0,
    "auto": 1,
    "game": 1,
    "forget": 0,
    "means": [
        {
            "id": 1209,
            "meanWord": "qwe",
            "type": {
                "id": 3,
                "name": "adj"
            }
        }
    ],
    "tags": [
        {
            "id": 4,
            "name": "leisure time, community"
        }
    ],
    "relationWords": [
        {
            "id": 917,
            "relationType": "RELATED",
            "wordRelationId": 815,
            "wordRelation": "voluntary"
        }
    ]
}
```
</details>

<details>
<summary>Delete Word: Xóa từ</summary>

**Request**
```sh
curl --location --request DELETE 'http://localhost:8080/word/1168' \
--header 'Authorization: Bearer <ACCESS_TOKEN> \
```
**Response**
```text
Deleted
```
</details>

## Tag
Gắn thẻ cho các từ tiếng Anh

<details>
<summary>Query Tag: Lấy danh sách thẻ</summary>

**Request**
```sh
curl --location 'http://localhost:8080/tag/query?page=1&size=20&sortBy=Id&sortType=ASC' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```json
[
    {
        "id": 1,
        "name": "sentence"
    },
    {
        "id": 2,
        "name": "recruitment"
    },
    {
        "id": 3,
        "name": "phrase"
    },
    ......
]
```
</details>

<details>
<summary>Show Tag: Lấy thông tin các từ tiếng Anh thuộc một thẻ</summary>

**Request**
```sh
curl --location 'http://localhost:8080/tag/show?value=recruitment' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```json
{
    "id": 2,
    "name": "recruitment",
    "words": [
        {
            "id": 1001,
            "english": "recruiter",
            "date": "07-03-2023",
            "notification": 0,
            "auto": 1,
            "game": 1,
            "forget": 0,
            "means": [
                {
                    "id": 166,
                    "meanWord": "nhà tuyển dụng, người tuyển dụng",
                    "type": {
                        "id": 6,
                        "name": "n"
                    }
                }
            ],
            "tags": [
                {
                    "id": 2,
                    "name": "recruitment"
                }
            ],
            "relationWords": [
                {
                    "id": 73,
                    "relationType": "RELATED",
                    "wordRelationId": 1003,
                    "wordRelation": "recruit"
                },
                {
                    "id": 77,
                    "relationType": "RELATED",
                    "wordRelationId": 1002,
                    "wordRelation": "recruitment"
                }
            ]
        },
        {
            "id": 1002,
            "english": "recruitment",
            "date": "07-03-2023",
            "notification": 0,
            "auto": 1,
            "game": 1,
            "forget": 0,
            "means": [
                {
                    "id": 165,
                    "meanWord": "sự tuyển dụng",
                    "type": {
                        "id": 6,
                        "name": "n"
                    }
                }
            ],
            "tags": [
                {
                    "id": 2,
                    "name": "recruitment"
                }
            ],
            "relationWords": [
                {
                    "id": 59,
                    "relationType": "RELATED",
                    "wordRelationId": 1003,
                    "wordRelation": "recruit"
                },
                {
                    "id": 77,
                    "relationType": "RELATED",
                    "wordRelationId": 1001,
                    "wordRelation": "recruiter"
                }
            ]
        },
        .....
    ]
}
```
</details>

<details>
<summary>Insert Tag: Thêm môt thẻ mới</summary>

**Request**
```sh
curl --location 'http://localhost:8080/tag' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <ACCESS_TOKEN> \
--data '{
    "name": "new year"
}'
```
**Response**
```json
{
    "id": 11,
    "name": "new year"
}
```
</details>

<details>
<summary>Delete Tag: Xóa thẻ</summary>

**Request**
```sh
curl --location --request DELETE 'http://localhost:8080/tag/10' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```text
Deleted
```
</details>


## Type
Loại từ của các từ tiếng Anh

<details>
<summary>Query type: Lấy danh sách loại từ</summary>

**Request**
```sh
curl --location 'http://localhost:8080/type/query?page=1&size=20&sortBy=Id&sortType=ASC' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```json
[
    {
        "id": 1,
        "name": "adv"
    },
    {
        "id": 2,
        "name": "v"
    },
    {
        "id": 3,
        "name": "adj"
    },
    ......
]
```
</details>

<details>
<summary>Insert Type: Thêm môt loại từ mới</summary>

**Request**
```sh
curl --location 'http://localhost:8080/type' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <ACCESS_TOKEN> \
--data '{
    "name": "sss"
}'
```
**Response**
```json
{
    "id": 9,
    "name": "sss"
}
```
</details>

<details>
<summary>Delete Type: Xóa loại từ</summary>

**Request**
```sh
curl --location --request DELETE 'http://localhost:8080/type/9' \
--header 'Authorization: Bearer <ACCESS_TOKEN>
```
**Response**
```text
Deleted
```
</details>



***Lưu ý:*** *<ACCESS_TOKEN> là token được lấy khi đã **Authorize** ở trên.*




















