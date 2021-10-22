# cexup-mobile-app

this is monorepo for mobile app of cexup

## stack
Tech stack that use the app's

- [ ] [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ ] [Kotlin](https://kotlinlang.org/)
- [ ] [Firebase](https://square.github.io/retrofit/)

## Data Structure

### User

Field|Desc
----|----
uid | uniq
phone | number
name | string
username | string
alamat | string
level | string(penyewa,pengepul,petani)
created_at|long
updated_at|long


### Store/Toko

Field| Desc
---- | ----
uid| unique
pengepul_uid| uniq
nama_toko|string
description|string
alamat_toko|string
telp_toko | string
logo|string
lattitude|long
longitude|long
created_at|long
updated_at|long

### Produk/Komoditi

Field | Desc
----- | ----
uid| unique
store_uid | unique
name_produk | string
category|string
price| number
thumbnail | string(should store url picture)
created_at|long
updated_at | long


### Transaction(On going)

Field | Desc
---- | ---
created_at|long
updated_at | long


### Kurs
Field|Desc
---- | ----
uid|unique
idr|number
usd|number
created_at|long
updated_at | long




