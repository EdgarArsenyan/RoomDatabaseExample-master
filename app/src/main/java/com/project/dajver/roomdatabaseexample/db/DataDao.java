package com.project.dajver.roomdatabaseexample.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.project.dajver.roomdatabaseexample.db.model.DataModel;

import java.util.List;

/**
 * Created by gleb on 11/16/17.
 */

@Dao
public interface DataDao {
    @Insert
    void insert(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllData();

    @Update
    void update(DataModel dataModel);

//    @Query("SELECT * FROM DataModel WHERE title LIKE :title")
//    List<DataModel> getAllInfo();
}
