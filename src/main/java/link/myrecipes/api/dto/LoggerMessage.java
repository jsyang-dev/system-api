package link.myrecipes.api.dto;

import link.myrecipes.api.domain.LogCallDocument;
import link.myrecipes.api.domain.LogFailDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LoggerMessage {
    private String logType;

    private String system;

    private String method;

    private String arguments;

    private String exception;

    private String exceptionMessage;

    private String registerDate;

    @Builder
    public LoggerMessage(String logType, String system, String method, String arguments, String exception, String exceptionMessage, String registerDate) {
        this.logType = logType;
        this.system = system;
        this.method = method;
        this.arguments = arguments;
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
        this.registerDate = registerDate;
    }

    public LogCallDocument toLogCallDocument() {
        return LogCallDocument.builder()
                .system(this.getSystem())
                .method(this.getMethod())
                .arguments(this.getArguments())
                .registerDate(this.getRegisterDate())
                .build();
    }

    public LogFailDocument toLogFailDocument() {
        return LogFailDocument.builder()
                .system(this.getSystem())
                .method(this.getMethod())
                .arguments(this.getArguments())
                .exception(this.getException())
                .exceptionMessage(this.getExceptionMessage())
                .registerDate(this.getRegisterDate())
                .build();
    }
}
