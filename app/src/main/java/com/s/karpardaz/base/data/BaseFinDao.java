package com.s.karpardaz.base.data;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.s.karpardaz.base.model.BaseFin;

import java.util.List;

public interface BaseFinDao<Entity extends BaseFin> {

    @Insert
    Long insert(Entity fin);

    LiveData<List<Entity>> getAllFins(String stockId);

    LiveData<List<Entity>> getFinsByDate(String startDate, String endDate, String stockId);

}
