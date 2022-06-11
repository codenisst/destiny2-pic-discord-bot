package ru.codenisst.destiny2pic.vk.servises;

import ru.codenisst.destiny2pic.bot.speech.Returner;

public enum ContentType implements Returner {

    PHOTO {
        @Override
        public String get() {
            return "photo";
        }
    },
    VIDEO {
        @Override
        public String get() {
            return "video";
        }
    },
    AUDIO {
        @Override
        public String get() {
            return "audio";
        }
    },
    LINKS {
        @Override
        public String get() {
            return "link";
        }
    },
    DOCS {
        @Override
        public String get() {
            return "doc";
        }
    }
}
