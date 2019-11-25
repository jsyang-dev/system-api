package link.myrecipes.api.service;

import link.myrecipes.api.dto.LoggerMessage;

public interface LoggerService {
    void receiveMessage(LoggerMessage loggerMessage);
}
