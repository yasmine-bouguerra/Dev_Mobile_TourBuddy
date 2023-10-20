package tn.esprit.tourbuddy.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import tn.esprit.tourbuddy.dao.CommentDao;
import tn.esprit.tourbuddy.dao.PublicationDao;
import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;

@Database(entities = {Publication.class, Comment.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PublicationDao publicationDao();
    public abstract CommentDao commentDao();
}
