# Kopra.id

this is monorepo for mobile app of kopra

## stack
Tech stack that use the app's

- [ ] [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ ] [Kotlin](https://kotlinlang.org/)
- [ ] [Firebase](https://square.github.io/retrofit/)

## Data Structure

### User

Field|Desc
----|----
uid | unique
phoneNumber | number
fullName | string
address | string
latitude|long
longitude|long
level | string(penyewa,pengepul,petani)
createdAt|long
updatedAt|long


### Store/Toko

Field| Desc
---- | ----
uid| unique
tenantUid| unique
storeName|string
description|string
addressStore|string
phoneNumber | string
logo|string
latitude|long
longitude|long
createdAt|long
updatedAt|long

### Produk/Komoditi

Field | Desc
----- | ----
uid| unique
storeUid | unique
productName | string
category|string
price| number
unit|enum(kg,days/hari)
thumbnail | string(should store url picture)
createdAt|long
updatedAt|long


### Transaction(On going)

Field | Desc
---- | ----
uid|unique
buyer|unique
seller|unique
totalPrice|number
detail|Array(Detail Transaction)
createdAt|long
updatedAt|long


### Detail Transaction(On going)
Field | Desc
---- | ----
uid|unique
transactionUid|unique
productUid|unique
quantity|number
price|number
createdAt|long
updatedAt|long

### Kurs
Field|Desc
---- | ----
uid|unique
idr|number
usd|number
createdAt|long
updatedAt|long




