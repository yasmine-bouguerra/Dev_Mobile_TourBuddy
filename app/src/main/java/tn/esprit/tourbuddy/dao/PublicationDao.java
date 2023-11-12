package tn.esprit.tourbuddy.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.tourbuddy.entity.Publication;


@Dao
public interface PublicationDao {
    @Insert
    void insererPublication(Publication publication);

    @Update
    void modifierPublication(Publication publication);

    @Delete
    void supprimerPublication(Publication publication);

    @Query("SELECT * FROM table_publication")
    List<Publication> getAll() ;

    @Query("SELECT * FROM table_publication WHERE pid = :publicationId")
    Publication getPublicationById(long publicationId);
}
