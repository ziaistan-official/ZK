package juloo.keyboard2;

import org.json.JSONException;
import org.json.JSONObject;

public class ClipboardItem {
    private static final String JSON_TEXT = "text";
    private static final String JSON_PINNED = "pinned";
    private static final String JSON_TIMESTAMP = "timestamp";

    private final String text;
    private long timestamp;
    private boolean pinned;

    public ClipboardItem(String text, long timestamp, boolean pinned) {
        this.text = text;
        this.timestamp = timestamp;
        this.pinned = pinned;
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClipboardItem that = (ClipboardItem) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_TEXT, text);
        json.put(JSON_TIMESTAMP, timestamp);
        json.put(JSON_PINNED, pinned);
        return json;
    }

    public static ClipboardItem fromJSON(JSONObject json) throws JSONException {
        String text = json.getString(JSON_TEXT);
        long timestamp = json.getLong(JSON_TIMESTAMP);
        boolean pinned = json.getBoolean(JSON_PINNED);
        return new ClipboardItem(text, timestamp, pinned);
    }
}