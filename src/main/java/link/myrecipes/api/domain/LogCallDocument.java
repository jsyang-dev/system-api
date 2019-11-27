package link.myrecipes.api.domain;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_call")
@Getter
public class LogCallDocument {
    @Id
    private ObjectId id;

    private String system;

    private String method;

    private String arguments;

    private String registerDate;

    @Builder
    public LogCallDocument(String system, String method, String arguments, String registerDate) {
        this.system = system;
        this.method = method;
        this.arguments = arguments;
        this.registerDate = registerDate;
    }
}
