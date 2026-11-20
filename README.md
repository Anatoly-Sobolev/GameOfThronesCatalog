# Каталог персонажей «Игры престолов»

Лабораторная работа № 4. Каталог загружает персонажей из An API of Ice and Fire страницами по 20. Поиск обращается к серверу по полному имени, например Jon Snow. Карточка запрашивается отдельно.

[Отчёт по ЛР 4](docs/lab-4/report.docx)

## Возможности

- просмотр списка персонажей;
- поиск персонажа по имени;
- отображение заглушки вместо изображения;
- просмотр подробной информации о персонаже;
- переход к отцу персонажа, если он указан в исходных данных;
- возврат к списку с сохранением запроса и положения прокрутки;
- переключение светлой и тёмной темы;
- переключение русского и английского языка;
- обратная анимация при возврате на предыдущий экран.

## Скриншоты предыдущей вехи

### Каталог

| Светлая тема | Тёмная тема | Английский язык |
| --- | --- | --- |
| ![Каталог на русском языке в светлой теме](docs/lab-1/screenshots/catalog-light-ru.jpg) | ![Каталог на русском языке в тёмной теме](docs/lab-1/screenshots/catalog-dark-ru.jpg) | ![Каталог на английском языке](docs/lab-1/screenshots/catalog-light-en.jpg) |

### Детальная информация и поиск

| Связь с отцом персонажа | Пустой результат поиска |
| --- | --- |
| ![Экран персонажа с переходом к отцу](docs/lab-1/screenshots/character-detail-en.jpg) | ![Сообщение о том, что ничего не найдено](docs/lab-1/screenshots/search-empty-state-ru.jpg) |

## Использованные технологии

- Kotlin Multiplatform;
- Compose Multiplatform и Material 3;
- Navigation 3;
- ViewModel, StateFlow и корутины;
- Compose Resources для локализации интерфейса.

## Структура проекта

- `shared` — общий код, экраны, состояние, навигация, ресурсы и тестовые данные;
- `desktopApp` — точка запуска версии для компьютера;
- `androidApp` — приложение для Android;
- `webApp` — приложение для браузера;
- `iosApp` — проект приложения для iOS;
- `tools` — проверка совпадения ключей локализации.

## Запуск на компьютере

Windows:

```powershell
.\gradlew.bat :desktopApp:run
```

macOS и Linux:

```bash
./gradlew :desktopApp:run
```

## Сборка Android-приложения

```powershell
.\gradlew.bat :androidApp:assembleDebug
```

На macOS и Linux используется та же задача через `./gradlew`.

## Запуск в браузере

```powershell
.\gradlew.bat :webApp:wasmJsBrowserDevelopmentRun
```

На macOS и Linux:

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
```

## Проверка локализации

```powershell
python tools\check-strings.py
```
