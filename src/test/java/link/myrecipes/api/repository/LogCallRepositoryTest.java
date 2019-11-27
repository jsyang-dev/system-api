package link.myrecipes.api.repository;

import link.myrecipes.api.domain.LogCallDocument;
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
public class LogCallRepositoryTest {
    @Autowired
    private LogCallRepository logCallRepository;

    @Test
    public void When_호출로그_저장_Then_동일한_도메인_반환() {
        //given
        LogCallDocument logCallDocument = LogCallDocument.builder()
                .system("myrecipes-api")
                .method("MemberController.login(..)")
                .arguments("[user]")
                .registerDate("2019-11-25T17:26:11.632")
                .build();
        LogCallDocument savedLogCallDocument = this.logCallRepository.save(logCallDocument);

        //when
        Optional<LogCallDocument> logCallDocumentOptional = this.logCallRepository.findById(savedLogCallDocument.getId());
        if (!logCallDocumentOptional.isPresent()) {
            throw new NotExistDataException(LogCallDocument.class, savedLogCallDocument.getId());
        }

        //then
        assertThat(logCallDocumentOptional.get(), instanceOf(LogCallDocument.class));
        assertThat(logCallDocumentOptional.get().getSystem(), is(logCallDocument.getSystem()));
        assertThat(logCallDocumentOptional.get().getMethod(), is(logCallDocument.getMethod()));
        assertThat(logCallDocumentOptional.get().getArguments(), is(logCallDocument.getArguments()));
        assertThat(logCallDocumentOptional.get().getRegisterDate(), is(logCallDocument.getRegisterDate()));
    }
}