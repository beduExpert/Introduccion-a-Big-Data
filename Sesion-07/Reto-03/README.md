# Reto 03- Implementación de Acciones en Spark mediante Python y Scala

## Objetivo

* Poner en práctica las acciones elementales de Spark mediante Scala y Python para el análisis y administración de datos

## Desarrollo

A continuación, te presento los siguientes ejercicios donde pondrás en práctica algunas de las acciones elementales en Spark:

# collect()

Con ayuda de Python en Spark, deberás:
- Un loop que coleccione todos los elementos entre el 51 y el 61
- Un loop que coleccione todos los elementos entre el 201 y el 211

# count()

Con ayuda de _Scala_ en Spark, deberás:
- Deberás generar un pequeño loop que cuente los elementos el array [45,85,78,95,85,785,854,21,36,52,14,85,23,65,458,8521,255,456,96,132,58,7854,458,7455,878,4554889,4565,4554,455,65,1,1201,001,1223,2021,4511,814,54,5151511,12,168,452,122,12,87,459]
- Deberás generar un pequeño loop que cuente los elementos el array [98,45,87,45,98,78,52,125,458,789,65,4587,78554,87858,9521,125,465,789,456,21235,8456,459,4654,45,1,23,4,5,8,7,4,5,2,1,6,9,8,7,4,65,165,4562,12648,45621,45699,785,1364,45654,7895,45687,421654,467,78954,4651,4568,745614,4549674,564,654,567,448,7,5,96,56,546,41657,45,741,463,4512,414564,5,784,87,415,45,48,7,985,4654,6788,48674,6574,68,465,122535,68,789,514235,245,64,586,415,1454,53,4,6553,1453,4132,1453,4,5643213,4,54,53,153,41,51,354,51,35,153,13,5,4351,3541,3541,135413,51,354,853,35,4351,351,35,135,3,4,3135,1531,53,12354,5489,41524658,415134,546416,451465484,35415454,5454121,12321521,102154,121315]

# take()

Con la ayuda de Python, reconsidera los dos arrays propuestos para desarrollar el ejercicio pasado, y usa la función take para obtener los dos primeros elementos de cada uno de ellos

# top()

Con la ayuda de Python, haz que Spark te muestre el top de los siguientes arrays:
- [13,4561,1232,154,45687,45121,455687,4561213]
- [2356,988,78454,1334,45621,457,985,45521,456512,1235]
- [123,456897,45123,55654,78413,54568,789454,12321,4556214,78452,1354]

# takeOrdered()

Con la ayuda de Python, implemente TakeOrdered() a los siguientes arrays:
- [98,45,87,45,98,78,52,125,458,789,65,4587,78554,87858,9521,125,465,789,456,21235,8456,459,4654,45,1,23,4,5,8,7,4,5,2,1,6,9,8,7,4,65,165,4562,12648,45621,45699,785,1364,45654,7895,45687,421654,467,78954,4651,4568,745614,4549674,564,654,567,448,7,5,96,56,546,41657,45,741,463,4512,414564,5,784,87,415,45,48,7,985,4654,6788,48674,6574,68,465,122535,68,789,514235,245,64,586,415,1454,53,4,6553,1453,4132,1453,4,5643213,4,54,53,153,41,51,354,51,35,153,13,5,4351,3541,3541,135413,51,354,853,35,4351,351,35,135,3,4,3135,1531,53,12354,5489,41524658,415134,546416,451465484,35415454,5454121,12321521,102154,121315]
- [123154,13245,45615451,65413545,45315151,61351,5313135135,1351351351,3515315313,531531351,531351351,3513513153,53135131,53355396,9865,265,98,42,656,596,859897,42146596,867898,1,2,352658,3,4,8456,5689,789,54,652,654,85,652,145,87,523,65456]

# first()

Con la ayuda de Python, aplique first a números en un rango de entre 126546 321458 y otro a una serie de números en un rango de entre 897 y 1025

# countByValue()

Con la ayuda de Python, Aplique countByValue() a los siguientes arrays:
- ("Abelardo","Amaury","Arlen","Pablito", "Teresa", "Mauritano", "Abelardo","Dany", "Dom", "Dany", "Abelardo", "Amaury")
- ("Panchito", "Soler","Santi", "Santa", "Moni", "Santi", "Panchito")
- ("Iñaqui", "Lau", "Sergio", "Sergio", "Sergio", "Iñaqui","Iñaqui","Iñaqui")