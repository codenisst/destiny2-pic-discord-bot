package ru.codenisst.destiny2pic.vk.models.postschema;

import java.io.Serializable;
import java.util.ArrayList;

public class Audio implements Serializable {
    private String artist;
    private int id;
    private int owner_id;
    private String title;
    private int duration;
    private boolean is_explicit;
    private boolean is_focus_track;
    private String track_code;
    private String url;
    private int date;
    private ArrayList<MainArtist> main_artists;
    private boolean short_videos_allowed;
    private boolean stories_allowed;
    private boolean stories_cover_allowed;

    public String getArtist() {
        return artist;
    }

    public int getId() {
        return id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isIs_explicit() {
        return is_explicit;
    }

    public boolean isIs_focus_track() {
        return is_focus_track;
    }

    public String getTrack_code() {
        return track_code;
    }

    public String getUrl() {
        return url;
    }

    public int getDate() {
        return date;
    }

    public ArrayList<MainArtist> getMain_artists() {
        return main_artists;
    }

    public boolean isShort_videos_allowed() {
        return short_videos_allowed;
    }

    public boolean isStories_allowed() {
        return stories_allowed;
    }

    public boolean isStories_cover_allowed() {
        return stories_cover_allowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audio audio = (Audio) o;

        if (id != audio.id) return false;
        if (owner_id != audio.owner_id) return false;
        if (duration != audio.duration) return false;
        if (is_explicit != audio.is_explicit) return false;
        if (is_focus_track != audio.is_focus_track) return false;
        if (date != audio.date) return false;
        if (short_videos_allowed != audio.short_videos_allowed) return false;
        if (stories_allowed != audio.stories_allowed) return false;
        if (stories_cover_allowed != audio.stories_cover_allowed) return false;
        if (artist != null ? !artist.equals(audio.artist) : audio.artist != null) return false;
        if (title != null ? !title.equals(audio.title) : audio.title != null) return false;
        if (track_code != null ? !track_code.equals(audio.track_code) : audio.track_code != null) return false;
        if (url != null ? !url.equals(audio.url) : audio.url != null) return false;
        return main_artists != null ? main_artists.equals(audio.main_artists) : audio.main_artists == null;
    }

    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + owner_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (is_explicit ? 1 : 0);
        result = 31 * result + (is_focus_track ? 1 : 0);
        result = 31 * result + (track_code != null ? track_code.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + date;
        result = 31 * result + (main_artists != null ? main_artists.hashCode() : 0);
        result = 31 * result + (short_videos_allowed ? 1 : 0);
        result = 31 * result + (stories_allowed ? 1 : 0);
        result = 31 * result + (stories_cover_allowed ? 1 : 0);
        return result;
    }
}
