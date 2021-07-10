package com.s.karpardaz.income.model

import androidx.room.Entity
import androidx.room.Index
import com.s.karpardaz.base.model.BaseEntity
import java.util.*

@Entity(indices = [Index(value = ["uuid", "id"], unique = true)])
class Income(
        id: Long, uuid: String, var atDate: String, var subject: String,
        var amount: String, var stockId: String, createdAt: String,
        updatedAt: String,
) : BaseEntity(id, uuid, createdAt, updatedAt) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Income) return false
        if (!super.equals(other)) return false
        val income = other
        return atDate == income.atDate && subject == income.subject && amount == income.amount && stockId == income.stockId
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), atDate, subject, amount, stockId)
    }

    override fun toString(): String {
        return "Income{" +
                "atDate='" + atDate + '\'' +
                ", subject='" + subject + '\'' +
                ", amount='" + amount + '\'' +
                ", stockId='" + stockId + '\'' +
                ", id=" + id +
                ", uuid='" + uuid + '\'' +
                "} " + super.toString()
    }
}