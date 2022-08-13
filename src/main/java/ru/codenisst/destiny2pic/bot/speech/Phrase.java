package ru.codenisst.destiny2pic.bot.speech;

public enum Phrase implements Returner {
    AUTO_ERROR {
        @Override
        public String get() {
            return "Я уже и так оповещаю тебя о новых постах \uD83D\uDE09";
        }
    },
    AUTO_CONFIRM {
        public String get() {
            return "Хорошо! Я периодически буду оповещать тебя о новых постах \uD83D\uDE42";
        }
    },
    NO_RIGHT {
        @Override
        public String get() {
            return "Извини, но у тебя нет на это прав \uD83E\uDD25";
        }
    },
    OFF_AUTO_ERROR {
        @Override
        public String get() {
            return "Я и так ничего не делаю \uD83D\uDE12";
        }
    },
    OFF_AUTO_CONFIRM {
        @Override
        public String get() {
            return "Никаких автоматических новостей, лады \uD83D\uDE14";
        }
    },
    NEW_POST {
        @Override
        public String get() {
            return "\uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89 " +
                    "**Новые постики с картиночками!**" +
                    " \uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89";
        }
    },
    NOT_FOUND {
        @Override
        public String get() {
            return "Новых картиночек не найдено..." + " \uD83D\uDE22";
        }
    },
    ALARM {
        @Override
        public String get() {
            return "**Срочно обратись к админу!** \n";
        }
    },
    DELIMITER {
        @Override
        public String get() {
            return "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n";
        }
    },
    WHAT_GROUP_TO_ADD {
        @Override
        public String get() {
            return """
                    С какой группой начать работать?
                    ```Напиши в любом нижеизложенном варианте:
                    1) (ссылка на группу)
                    2) (ссылка на группу) : (тип контента)
                    3) (ссылка на группу) : (количество постов)
                    4) (ссылка на группу) : (количество постов) : (тип контента)```
                    _Тип контента может быть указан из следующих: photo .
                    Если не указано количество запрашиваемых постов, то по дефолту будет парситься 5 последних постов.
                    Если не указан тип контента, то по умолчанию будут парситься только посты с изображениями._

                    **! ВАЖНО !**
                    _После указания групп не забудьте использовать команду_ ***!stop***""";
        }
    },
    GROUP_ALREADY_ADDED {
        @Override
        public String get() {
            return "Эта группа уже была добавлена!";
        }
    },
    HELP {
        @Override
        public String get() {
            return """
                        **Вот что я умею!**

                          - команды для обычных пользователей:
                        ```
                        !picture - я проверю, появились ли новые посты. Если да - пришлю их сюда.
                        !help - получить справку по командам
                        ```
                          - команды для модераторов:
                        ```
                        !groupList - публикуется список групп, из которых парсятся посты на данный момент (*)
                        !addGroups - позволяет добавить в наблюдение новую группу (группы), по определенным паттернам (*)
                        !deleteGroups - позволяет удалить из наблюдения группу (*) (группы)
                        !deleteAllGroups - удаляет ВСЕ группы из парсера бота (*)
                        !auto - включает автопостинг (*) (по дефолту парсинг и постинг каждые 30 минут)
                        !offAuto - отключает автопостинг (*)
                        !status - позволяет сменить статус бота (*)
                        !disconnect - выключает бота (*)
                        ```
                        """;
        }
    },
    PLAYING {
        @Override
        public String get() {
            return "Укажите, во что мне играть!";
        }
    },
    NOT_FOUND_GROUP {
        @Override
        public String get() {
            return "Никаких групп не добавлено!";
        }
    },
    GROUP_ADDED {
        @Override
        public String get() {
            return "Группа добавлена!";
        }
    },
    WATCHING {
        @Override
        public String get() {
            return "Начинаю следить за этими группами!\n";
        }
    },
    GROUP_INFO {
        @Override
        public String get() {
            return "На данный момент я работаю над этими группами:";
        }
    },
    WHAT_GROUP_TO_DELETE {
        @Override
        public String get() {
            return """
                    Какую группу удалить?
                    
                    **! ВАЖНО !**
                    _После указания групп не забудьте использовать команду_ ***!stop***""";
        }
    },
    GROUP_IS_DELETED {
        @Override
        public String get() {
            return "Группа удалена!";
        }
    },
    NOTHING_DELETE {
        @Override
        public String get() {
            return "А удалять то и нечего было...";
        }
    },
    GROUP_MISSING {
        @Override
        public String get() {
            return "Таких групп в работе нет!";
        }
    },
    DISCONNECT {
        @Override
        public String get() {
            return "\uD83D\uDCA4";
        }
    },
    UNKNOWN_COMMAND {
        @Override
        public String get() {
            return "Неизвестная команда!";
        }
    },
    INCORRECT_LINK {
        @Override
        public String get() {
            return "Некорректная ссылка или паттерн сообщения!";
        }
    },
    ALL_GROUPS_REMOVED {
        @Override
        public String get() {
            return "Все группы удалены!";
        }
    }
}
