package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;
import java.util.ArrayList;

public class Restriction implements Serializable {
    private String title;
    private String text;
    private int blur;
    private int can_play;
    private int can_preview;
    private ArrayList<CardIcon> card_icon;
    private int disclaimer_type;
    private ArrayList<ListIcon> list_icon;
    private int always_shown;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getBlur() {
        return blur;
    }

    public int getCan_play() {
        return can_play;
    }

    public int getCan_preview() {
        return can_preview;
    }

    public ArrayList<CardIcon> getCard_icon() {
        return card_icon;
    }

    public int getDisclaimer_type() {
        return disclaimer_type;
    }

    public ArrayList<ListIcon> getList_icon() {
        return list_icon;
    }

    public int getAlways_shown() {
        return always_shown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restriction that = (Restriction) o;

        if (blur != that.blur) return false;
        if (can_play != that.can_play) return false;
        if (can_preview != that.can_preview) return false;
        if (disclaimer_type != that.disclaimer_type) return false;
        if (always_shown != that.always_shown) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (card_icon != null ? !card_icon.equals(that.card_icon) : that.card_icon != null) return false;
        return list_icon != null ? list_icon.equals(that.list_icon) : that.list_icon == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + blur;
        result = 31 * result + can_play;
        result = 31 * result + can_preview;
        result = 31 * result + (card_icon != null ? card_icon.hashCode() : 0);
        result = 31 * result + disclaimer_type;
        result = 31 * result + (list_icon != null ? list_icon.hashCode() : 0);
        result = 31 * result + always_shown;
        return result;
    }
}
