package com.s.karpardaz.cost.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.s.karpardaz.user.model.User;

import java.util.List;

public class UserCost {
    @Embedded
    private final User user;
    @Relation(parentColumn = "uuid", entityColumn = "userId")
    private final List<Cost> costs;

    public UserCost(User user, List<Cost> costs) {
        this.user = user;
        this.costs = costs;
    }

    public User getUser() {
        return user;
    }

    public List<Cost> getCosts() {
        return costs;
    }
}
