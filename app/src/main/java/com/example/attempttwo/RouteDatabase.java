package com.example.attempttwo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Route.class, version = 2)
public abstract class RouteDatabase extends RoomDatabase {

    private static RouteDatabase instance;

    public abstract RouteDao routeDao();

    public static synchronized RouteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RouteDatabase.class, "route_database")
                    .createFromAsset("routeDatabase.db")
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
/*
    private static Callback roomCallBack = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RouteDao routeDao;

        private PopulateDbAsyncTask(RouteDatabase db){
            routeDao = db.routeDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            routeDao.insert(new Route("slab", "Blue","V0-V1", "",
                                        1,0, 0));
            routeDao.insert(new Route("cave", "Red","V0-V1", "",
                    1,0, 0));
            routeDao.insert(new Route("Overhang", "Orange","V1-V2", "",
                    1,0, 0));
            return null;
        }
    }
*/
}
