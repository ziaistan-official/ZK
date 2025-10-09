package juloo.keyboard2;

import android.content.Context;
import android.content.res.XmlResourceParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AutoCorrectionProvider {

    private final Map<String, String> corrections = new HashMap<>();
    private volatile boolean loaded = false;

    public AutoCorrectionProvider(Context context) {
        new Thread(() -> {
            loadCorrections(context);
            loaded = true;
        }).start();
    }

    private void loadCorrections(Context context) {
        XmlResourceParser parser = context.getResources().getXml(R.xml.auto_correct);
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && "c".equals(parser.getName())) {
                    String from = parser.getAttributeValue(null, "from");
                    String to = parser.getAttributeValue(null, "to");
                    if (from != null && to != null) {
                        corrections.put(from.toLowerCase(), to);
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        } finally {
            parser.close();
        }
    }

    public String getCorrection(String word) {
        if (!loaded) {
            return null;
        }
        return corrections.get(word);
    }
}