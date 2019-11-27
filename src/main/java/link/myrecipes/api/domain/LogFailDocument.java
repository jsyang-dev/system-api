package link.myrecipes.api.domain;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_fail")
@Getter
public class LogFailDocument {
    @Id
    private ObjectId id;

    private String system;

    private String method;

    private String arguments;

    private String exception;

    private String exceptionMessage;

    private String registerDate;

    @Builder
    public LogFailDocument(String system, String method, String arguments, String exception, String exceptionMessage, String registerDate) {
        this.system = system;
        this.method = method;
        this.arguments = arguments;
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
        this.registerDate = registerDate;
    }
}
