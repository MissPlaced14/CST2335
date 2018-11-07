package com.example.patty.andoridlabs;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherForcast extends Activity {
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.visable);
    }

    private class ForcastQuery extends AsyncTask {
        public String currentTemp;
        public String maxTemp;
        public String minTemp;
        public String windSpd;
        XmlPullParser parser;
        private String urlString =
                "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

        @Override
        protected Object doInBackground(Object[] args) {
//            private InputStream downloadUrl(String urlString) throws IOException {
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                return conn.getInputStream();

//            }

        }
        private final String ns = null;

        public List parse(InputStream in) throws XmlPullParserException, IOException {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
                return readFeed(parser);
            } finally {
                in.close();
            }
            private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
                List entries = new ArrayList();

                parser.require(XmlPullParser.START_TAG, ns, "feed");
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                    String name = parser.getName();
                    // Starts by looking for the entry tag
                    if (name.equals("entry")) {
                        entries.add(readEntry(parser));
                    } else {
                        skip(parser);
                    }
                }
                return entries;
            }
            class Entry {
                public final String title;
                public final String link;
                public final String summary;

                private Entry(String title, String summary, String link) {
                    this.title = title;
                    this.summary = summary;
                    this.link = link;
                }
            }
    }
}
