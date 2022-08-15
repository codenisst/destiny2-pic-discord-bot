![logo](https://i.ibb.co/s20x2Yf/logo.png)

# Discord-bot "Destiny2Pic".

Parses images from the specified public group in the social network "VKontakte" and sends them
to the server channel in Discord from which the request was received.

This bot was developed for a specific task, but note that it can be adapted for parsing
any data from social network "VKontakte" (via VK API).

ATTENTION: The bot requires unique data, such as "botToken", "mainRole",
"userId", "access_token", "domain", in botConfig.properties and vkConfig.properties files (users whose role name 
is specified in the "mainRole" field can control the bot).

Learn more here -> [Discord API](https://discord.com/developers), [VK API](https://dev.vk.com/api/access-token/getting-started)

### ! List of commands !
Commands marked with (*) are only available to users with "mainRole" role (see botConfig.properties).
```
!picture - parses and sends new posts with pictures to the chat
!help - get help on commands
!enableAuto (*) - turn on autoposting (by default parsing and posting occurs every 30 minutes)
!disableAuto (*) - turn off autoposting 
!status (*) - allows to change bot's status
!exit (*) - disconnects bot
!addGroups (*) - allows you to add groups to the bot parser
!showGroups - sends the list of groups, with which the bot parser is working at the moment
!deleteGroups (*) - deletes groups from the bot parser
!deleteAllGroups (*) - deletes ALL groups from the bot parser

*functional is gradually expanding*
```

---------------------------------------------------------------------------

# Discord-бот "Destiny2Pic".

Парсит изображения из указанной публичной группы в социальной сети "ВКонтакте" и присылает их 
в тот канал сервера в Discord, из которого поступил запрос.

Данный бот был разработан под определенную задачу, но учтите, что его можно адаптировать для парсинга
любых данных из социальной сети ВКонтакте (через VK API).

ВНИМАНИЕ: Для работы бота требуется указание уникальных данных, таких как "botToken", "mainRole",
"userId", "access_token", "domain", в файлах botConfig.properties и vkConfig.properties (пользователи с ролью, 
название которой указано в поле "mainRole", могут управлять ботом).

Больше вы можете узнать тут -> [Discord API](https://discord.com/developers), [VK API](https://dev.vk.com/api/access-token/getting-started)

### ! Список команд !
Команды, помеченные (*), доступны только пользователям с ролью "mainRole" (смотреть botConfig.properties).
```
!picture - парсит и отправляет в чат новые посты с изображениями
!help - присылает справку по командам
!enableAuto (*) - включает автопостинг (по дефолту парсинг и постинг проиcходит каждые 30 минут)
!disableAuto (*) - отключает автопостинг
!status (*) - позволяет сменить статус бота
!exit (*) - выключает бота
!addGroups (*) - позволяет добавлять группы в парсер бота
!showGroups - присылает список групп, с которыми на данный момент работает парсер
!deleteGroups (*) - удаляет группы из парсера бота
!deleteAllGroups (*) - удаляет ВСЕ группы из парсера бота

*функционал постепенно дополняется*
```