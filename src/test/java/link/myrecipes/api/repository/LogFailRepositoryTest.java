package link.myrecipes.api.repository;

import link.myrecipes.api.domain.LogCallDocument;
import link.myrecipes.api.domain.LogFailDocument;
import link.myrecipes.api.exception.NotExistDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LogFailRepositoryTest {
    @Autowired
    private LogFailRepository logFailRepository;

    @Test
    public void When_실패로그_저장_Then_동일한_도메인_반환() {
        //given
        LogFailDocument logFailDocument = LogFailDocument.builder()
                .system("myrecipes-api")
                .method("MemberController.login(..)")
                .arguments("[user]")
                .exception("UsernameNotFoundException")
                .exceptionMessage("User [user] does not exist.")
                .registerDate("2019-11-25T17:26:11.632")
                .build();
        LogFailDocument savedLogFailDocument = this.logFailRepository.save(logFailDocument);

        //when
        Optional<LogFailDocument> logFailDocumentOptional = this.logFailRepository.findById(savedLogFailDocument.getId());
        if (!logFailDocumentOptional.isPresent()) {
            throw new NotExistDataException(LogCallDocument.class, savedLogFailDocument.getId());
        }

        //then
        assertThat(logFailDocumentOptional.get(), instanceOf(LogFailDocument.class));
        assertThat(logFailDocumentOptional.get().getSystem(), is(logFailDocument.getSystem()));
        assertThat(logFailDocumentOptional.get().getMethod(), is(logFailDocument.getMethod()));
        assertThat(logFailDocumentOptional.get().getArguments(), is(logFailDocument.getArguments()));
        assertThat(logFailDocumentOptional.get().getRegisterDate(), is(logFailDocument.getRegisterDate()));
    }
}