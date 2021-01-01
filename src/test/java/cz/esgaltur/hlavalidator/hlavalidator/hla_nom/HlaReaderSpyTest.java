package cz.esgaltur.hlavalidator.hlavalidator.hla_nom;

import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusHLA;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ProgressBar;
import lombok.extern.apachecommons.CommonsLog;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@CommonsLog
class HlaReaderSpyTest {
    @SpyBean
    HlaNomReader hlaReaderTest;
    ProgressBar progressBar;

    /**
     *
     */
    @BeforeAll
    public static void initToolkit() {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });
    }

    /**
     *
     */
    @AfterAll
    static void after() {
        Platform.exit();
    }

    /**
     * @throws IOException
     */
    @BeforeEach
    public void before() throws IOException {
        progressBar = new ProgressBar();

        Mockito.when(hlaReaderTest.getStreamFromUrl())
                .thenReturn(getClass().getResourceAsStream("/hla_nom.txt"));

    }

    @Test
    void HlaReader_basic_setupValues() throws ParseException {
        String[] attributes = "A;1;19680101;;;".split(";");
        HlaNomRecord expected = HlaNomRecord.builder().
                locus(LocusHLA.builder().hla(HLA.A).build()).value("1")
                .dateAssigned(new SimpleDateFormat("yyyyMMdd").parse("19680101"))
                .dateDeleted(Date.from(Instant.ofEpochSecond(0)))
                .reason("")
                .identical("")
                .build();
        HlaNomRecord hlaNomRecord = new HlaNomRecord();
        hlaNomRecord.setUpValues(attributes);
        Assertions.assertEquals(
                hlaNomRecord.getLocus().getValue().ordinal(),
                expected.getLocus().getValue().ordinal()
        );
        Assertions.assertEquals(
                hlaNomRecord.getValue(),
                expected.getValue()
        );
        Assertions.assertEquals(
                hlaNomRecord.getDateAssigned(),
                expected.getDateAssigned()
        );
        Assertions.assertEquals(
                hlaNomRecord.getDateDeleted(),
                expected.getDateDeleted()
        );
        Assertions.assertEquals(
                hlaNomRecord.getReason(),
                expected.getReason()
        );
        Assertions.assertEquals(
                hlaNomRecord.getIdentical(),
                expected.getIdentical()
        );

    }

    @Test
    void HlaReader_basic_mockInputStreamsFromFile() throws ParseException {
        Optional<List<HlaNomRecord>> optionalHlaNomRecords = hlaReaderTest.readFromUrl(progressBar);
        Mockito.verify(hlaReaderTest, Mockito.times(1)).read(ArgumentMatchers.any(), ArgumentMatchers.any());
        MatcherAssert.assertThat(optionalHlaNomRecords.get().get(0),
                Matchers.equalTo(HlaNomRecord.builder().locus(LocusHLA.builder().hla(HLA.A).build()).value("1")
                        .dateAssigned(new SimpleDateFormat("yyyyMMdd").parse("19680101"))
                        .dateDeleted(Date.from(Instant.ofEpochSecond(0)))
                        .reason("")
                        .identical("")
                        .build()));


    }

    @Test
    void HlaReader_basic_isDeleted() throws ParseException {
        HlaNomRecord expected = HlaNomRecord.builder().
                locus(LocusHLA.builder().hla(HLA.A).build()).value("1")
                .dateAssigned(new SimpleDateFormat("yyyyMMdd").parse("19680101"))
                .dateDeleted(new SimpleDateFormat("yyyyMMdd").parse("19680101"))
                .reason("")
                .identical("")
                .build();
        Assertions.assertTrue(expected.isDeleted());
    }

    @Test
    void HlaReader_basic_isNotDeleted() throws ParseException {
        HlaNomRecord expected = HlaNomRecord.builder().
                locus(LocusHLA.builder().hla(HLA.A).build()).value("1")
                .dateAssigned(new SimpleDateFormat("yyyyMMdd").parse("19680101"))
                .dateDeleted(Date.from(Instant.ofEpochSecond(0)))
                .reason("")
                .identical("")
                .build();
        Assertions.assertFalse(expected.isDeleted());
    }


}
