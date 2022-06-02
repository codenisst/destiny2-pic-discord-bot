package ru.codenisst.discord2pic.bot.speech;

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
            return "\uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89 **Новые постики с картиночками!** \uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89";
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
            return "Срочно обратись к админу! ";
        }
    },
    DELIMITER {
        @Override
        public String get() {
            return "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n";
        }
    }

}
