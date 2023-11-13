package tn.esprit.tourbuddy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.tourbuddy.dao.CommentDao;
import tn.esprit.tourbuddy.dao.PublicationDao;
import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;

@Database(entities = {Publication.class, Comment.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;
    public abstract PublicationDao publicationDao();
    public abstract CommentDao commentDao();

    public static  AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "tour_buddy")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }



}