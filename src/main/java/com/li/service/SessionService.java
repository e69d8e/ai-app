package com.li.service;

import com.li.common.result.Result;

public interface SessionService {
    Result create();

    Result delete(String sessionId);

    Result getSessions(Integer page, Integer size);

    Result getSession(String sessionId);
}
