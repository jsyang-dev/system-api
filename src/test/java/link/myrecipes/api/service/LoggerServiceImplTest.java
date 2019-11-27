package link.myrecipes.api.service;

import link.myrecipes.api.domain.LogCallDocument;
import link.myrecipes.api.domain.LogFailDocument;
import link.myrecipes.api.dto.LoggerMessage;
import link.myrecipes.api.repository.LogCallRepository;
import link.myrecipes.api.repository.LogFailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoggerServiceImplTest {
    private LoggerMessage callMessage;
    private LoggerMessage failMessage;

    @InjectMocks
    private LoggerServiceImpl loggerService;

    @Mock
    private LogCallRepository logCallRepository;

    @Mock
    private LogFailRepository logFailRepository;

    @Before
    public void setUp() {
        this.callMessage = LoggerMessage.builder()
                .logType("call")
                .system("myrecipes-api")
                .method("MemberController.login(..)")
                .arguments("[user]")
                .registerDate("2019-11-25T17:26:11.632")
                .build();

        this.failMessage = LoggerMessage.builder()
                .logType("fail")
                .system("myrecipes-api")
                .method("MemberController.login(..)")
                .arguments("[user]")
                .exception("UsernameNotFoundException")
                .exceptionMessage("User [user] does not exist.")
                .registerDate("2019-11-25T17:26:11.632")
                .build();
    }

    @Test
    public void When_호출로그_저장_요청_Then_정상_저장() {
        //when
        this.loggerService.receiveMessage(this.callMessage);

        //then
        verify((this.logCallRepository), times(1)).save(any(LogCallDocument.class));
        verify((this.logFailRepository), never()).save(any(LogFailDocument.class));
    }

    @Test
    public void When_실패로그_저장_요청_Then_정상_저장() {
        //when
        this.loggerService.receiveMessage(this.failMessage);

        //then
        verify((this.logCallRepository), never()).save(any(LogCallDocument.class));
        verify((this.logFailRepository), times(1)).save(any(LogFailDocument.class));
    }
}