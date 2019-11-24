package link.myrecipes.api.service;

import link.myrecipes.api.dto.logger.LoggerMessage;

public interface LoggerService {
    void receiveMessage(LoggerMessage loggerMessage);
}
