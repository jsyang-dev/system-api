package link.myrecipes.api.service;

import link.myrecipes.api.dto.logger.LoggerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerServiceImpl implements LoggerService {
    @Override
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(LoggerMessage loggerMessage) {
        if ("call".equals(loggerMessage.getLogType())) {
            log.info("call: " + loggerMessage.toString());
        } else if ("fail".equals(loggerMessage.getLogType())) {
            log.info("fail: " + loggerMessage.toString());
        } else {
            log.info(loggerMessage.toString());
        }
    }
}
