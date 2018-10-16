# Тестовое задание (Java middle) 
Распознавание достоинства карт из скриншотов (recognizing suit-rank of cards from playing game screenshots)

- Дано множество картинок
- Необходимо написать программу на Java, которая распознает, какие карты лежат на столе (по центру картинки). Например, на этой картинке https://i.gyazo.com/65658f6ab114de07d5c08d5f81324dc7.png на столе лежат карты 4hQd7s
- Программа должна уметь распознавать карты в том числе на новых картинках, которые не были доступны на момент написания программы
- Допускаются ошибки в распознавании порядка 5%
- Нельзя использовать готовые библиотеки для распознавания текста. Необходимо написать свой алгоритм распознавания карт
- Программу нужно предоставить в виде, готовом к запуску на Windows десктопе. Файл run.cmd параметром принимает путь до папки с картинками. В консоль распечатывается результат в виде "имя файла - карты" для всех файлов папки