<img src="https://github.com/UnicoreProject/UnicoreConnect/blob/main/unicoreconnect.png?raw=true?v=2" />

# UnicoreConnect ![Kotlin](https://img.shields.io/badge/-Kotlin-05122A?style=flat&logo=Kotlin&logoColor=FFA518)&nbsp;
[![Build Status](https://github.com/ZirconiaStudio/ZirconiaConnect/actions/workflows/gradle.yml/badge.svg)](https://github.com/UnicoreProject/UnicoreConnect/actions)
[![Telegram](https://img.shields.io/endpoint?style=social&url=https://runkit.io/damiankrawczyk/telegram-badge/branches/master?url=https://t.me/unicore_project)](https://t.me/zirconiacms)

> Плагин для интеграции [UnicoreCMS](https://unicorecms.ru) с серверными платформами Spigot и Sponge

#### Поддерживаемые ядра
- Spigot/Bukkit 1.7.10 - 1.18.1, также их форки по типу PaperSpigot.
- Гибритные ядра: Thermos, Mohist, Magma и т. д.
- Sponge Vanilla и Sponge Forge.

#### Опциональные зависимости
* **LuckPerms** - включит модуль выдачи донат-прав/групп (Spigot/Sponge)
* **BanManager** - включит модуль двухнаправленной интеграции банов с сайтом (Spigot/Sponge)
* **Essentials/EssentialsX/CMI/Nucleus** - проверка на AFK модулем PlayTime (Spigot/Sponge)
* **Vault** - включит модуль внутриигровой экономики (Spigot)

## Установка и настройка
1. Создайте API-ключ с правом `unicore.kernel.connect`.
2. Поместите Jar-файл в папку плагинов.
3. Произведите настройку файла конфигурации UnicoreConnect.

```yaml
server: id сервера
api:
  url: Адрес UnicoreCMS-сервера
  key: API-ключ
```

## Сборка
UnicoreConnect использует Gradle для обработки зависимостей и сборки.

#### Зависимости
* Java 8 JDK или более поздней версии
* Git

#### Компиляция
```sh
git clone https://github.com/UnicoreProject/UnicoreConnect.git
cd UnicoreConnect/
./gradlew build
```

Собранный Jar-файл будет лежать в папке build/libs

## Команды и права

### Экономика
Команды | Пермишен | Описание |
| --- |  --- |  --- |
| /money, /bal, /balance | unicoreconnect.command.money | Баланс внутриигровой валюты |
| /moneytop, /baltop, /balancetop | unicoreconnect.command.moneytop | Топ 10 богачей сервера |
| /pay \[player\] \[amount\] | unicoreconnect.command.pay | Перевести монеты игроку |

### PlayTime
Команды | Пермишен | Описание |
| --- |  --- |  --- |
| /playtime, /pt | unicoreconnect.command.playtime | Время проведённое на сервере |
| /playtimetop, /pttop | unicoreconnect.command.playtimetop | Топ 10 по времени онлайн сервера |

### Склад и магазин
Команды | Пермишен | Описание |
| --- |  --- |  --- |
| /cart list | unicoreconnect.command.showcase.list | Список товаров на складе |
| /cart give \[id\] | unicoreconnect.command.showcase.give | Выдать товар со склада |
| /cart all | unicoreconnect.command.showcase.all | Выдать все товары со склада |
| /cart add \[price\] \[name\] | unicoreconnect.command.showcase.create | Добавить предмет, находящийся в руке в магазин |

### Администрирование
Команды | Пермишен | Описание |
| --- |  --- |  --- |
| /uc reload | unicoreconnect.admin | Перезагрузить плагин |
| /uc sync | unicoreconnect.admin | Переподключится к UnicoreServer |
