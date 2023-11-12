package tn.esprit.tourbuddy.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity( tableName = "table_claims")
public class Claims {

    @PrimaryKey(autoGenerate = true)
    public int clid;



    @ColumnInfo(name ="rec")
    private String rec;
    @ColumnInfo(name ="sujet")
    private String sujet;

    @Override
    public String toString() {
        return "Claims{" +
                "clid=" + clid +
                ", rec='" + rec + '\'' +
                ", sujet='" + sujet + '\'' +
                '}';
    }

    public Claims(int clid, String rec, int repId) {
        this.clid = clid;
        this.rec = rec;
        this.sujet= sujet;

    }

    public Claims() {
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }




}
