package ru.codenisst.destiny2pic.bot.listeners;

public enum Command {

    INSPECT_NEW_PICTURE("!picture"),
    SHOW_GROUPS("!showGroups"),
    ADD_GROUPS("!addGroups"),
    DELETE_GROUPS("!deleteGroups"),
    DELETE_ALL_GROUPS("!deleteAllGroups"),
    ENABLE_AUTO("!enableAuto"),
    DISABLE_AUTO("!disableAuto"),
    SET_STATUS("!status"),
    EXIT("!exit"),
    GET_HELP("!help");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
