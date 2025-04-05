package edu.cuny.chen.whereismystuff;

import android.app.Application;

import edu.cuny.chen.whereismystuff.model.ItemDatabase;

public class WhereIsMyStuffApplication extends Application {
    private static ItemDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = ItemDatabase.getDatabase(this);
    }

    public static ItemDatabase getDatabase() {
        return database;
    }
}
