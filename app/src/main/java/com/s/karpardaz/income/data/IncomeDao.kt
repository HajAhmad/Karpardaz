package com.s.karpardaz.income.data

import androidx.room.Dao
import androidx.room.Query
import com.s.karpardaz.base.dao.BaseDao
import com.s.karpardaz.income.model.Income
import io.reactivex.rxjava3.core.Flowable

@Dao
interface IncomeDao : BaseDao<Income> {
    @Query("select * from income where uuid = :uuid")
    fun getAll(uuid: String): List<Income>

    @Query("select * from income where atDate >= :startDate and atDate <= :endDate and uuid = :uuid")
    fun getByDate(startDate: String, endDate: String, uuid: String): List<Income>
}


class Test<T, E> {

    fun test(l: List<String>) {

    }

    fun test(s: ArrayList<Int>){

    }


}
