# Banking App

### Development
Build App
```shell
./gradlew build
```
Run App
```shell
./gradlew bootRun
```

Run Test
```shell
./gradlew test
```

### API DOC

**POST** /account/v1/create
#### Request:
> data-raw: owner [String] 
#### Response:
```json
{
    "owner": "Burkay Durdu",
    "accountNumber": "1746589712",
    "createdAt": "2021-08-11T23:32:23.944977"
}
```

**POST** /account/v1/credit/{accountNumber}
#### Request:
> data-raw: amount [Double]
#### Response:
```json
{
  "status": "OK",
  "approvalCode": null
}
```

**POST** /account/v1/debit/{accountNumber}
#### Request:
> data-raw: amount [Double]
#### Response:
```json
{
  "status": "OK",
  "approvalCode": null
}
```

**POST** /account/v1/phoneBillPayment/{accountNumber}
#### Request:
> data-raw:<br> amount [Double],<br> operator: [String],<br> phoneNumber: [String]
>> operator: [VODAFONE, TURKCELL, TURK_TELEKOM]

#### Response:
```json
{
  "status": "OK",
  "approvalCode": null
}
```

**GET** /account/v1/{accountNumber}
#### Request:
...
#### Response:
```json
{
  "accountNumber": "1746589712",
  "owner": "Burkay Durdu",
  "balance": 2800.0,
  "createdAt": "2021-08-11T23:32:23.944977",
  "transactions": [
    {
      "id": "d6cc52e2-511b-4dec-8b37-dd57087a301f",
      "amount": 1451.5,
      "createdAt": "2021-08-11T23:32:32.661976",
      "type": "DepositTransaction"
    },
    {
      "id": "19186b5d-2014-4d23-b534-e9c552dae2ce",
      "amount": 51.5,
      "createdAt": "2021-08-12T00:06:30.322633",
      "type": "WithdrawalTransaction"
    },
    {
      "id": "35aeaab6-b6f7-4e66-ae31-7094ea3124c3",
      "amount": 51.5,
      "createdAt": "2021-08-12T00:07:36.656753",
      "type": "PhoneBillPaymentTransaction",
      "operator": "TURKCELL",
      "phoneNumber": "05418436167"
    }
  ]
}
```

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-sql)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

