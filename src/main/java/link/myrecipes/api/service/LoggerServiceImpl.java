package link.myrecipes.api.service;

import link.myrecipes.api.dto.LoggerMessage;
import link.myrecipes.api.repository.LogCallRepository;
import link.myrecipes.api.repository.LogFailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerServiceImpl implements LoggerService {
    private final LogCallRepository logCallRepository;
    private final LogFailRepository logFailRepository;

    public LoggerServiceImpl(LogCallRepository logCallRepository, LogFailRepository logFailRepository) {
        this.logCallRepository = logCallRepository;
        this.logFailRepository = logFailRepository;
    }

    @Override
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(LoggerMessage loggerMessage) {
        log.info(">>>>> receive message: {}", loggerMessage.toString());
        if ("call".equals(loggerMessage.getLogType())) {
            logCallRepository.save(loggerMessage.toLogCallDocument());
        } else if ("fail".equals(loggerMessage.getLogType())) {
            logFailRepository.save(loggerMessage.toLogFailDocument());
        }
    }
}
