package com.example.android.alarmmanager;

/**
 * Created by Dell on 02-07-2015.
 */
    public class RssItem {
        // item title
        private String title;
        // item link
        private String link;
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getLink() {
            return link;
        }
        public void setLink(String link) {
            this.link = link;
        }
        @Override
        public String toString() {
            return title;
        }
    }

