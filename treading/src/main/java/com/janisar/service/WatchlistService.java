package com.janisar.service;

import com.janisar.model.Coin;
import com.janisar.model.User;
import com.janisar.model.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchList(Long userId) throws Exception;
    Watchlist createWatchList(User user);
    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin , User user) throws Exception;
}
