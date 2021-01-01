package cz.esgaltur.hlavalidator.hlavalidator.hla_nom;


import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.DNA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.HLA;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusDna;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.impl.LocusHLA;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ProgressBar;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HlaReaderMockTest {

    @MockBean
    HlaNomReader hlaReaderTest;

    ProgressBar progressBar;

    List<HlaNomRecord> hlaNomRecords;

    /**
     * @throws InterruptedException JavaFX platform Interrupted Exception
     */
    @BeforeAll
    public static void initToolkit()
            throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        // That's a pretty reasonable delay... Right?
        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    /**
     *
     */
    @AfterAll
    static void after() {
        Platform.exit();
    }

    @BeforeEach
    void before() {
        progressBar = mock(ProgressBar.class);
        hlaNomRecords = new ArrayList<>();
        hlaNomRecords.add(HlaNomRecord.builder()
                .locus(LocusDna.builder()
                        .dna(DNA.A).build()
                )
                .dateAssigned(Date.from(Instant.parse("2011-12-03T10:15:30.00Z")))
                .value("01:01:01:01")
                .build());
        hlaNomRecords.add(HlaNomRecord.builder()
                .locus(LocusDna.builder()
                        .dna(DNA.B).build()
                )
                .dateAssigned(Date.from(Instant.parse("2010-12-03T10:15:30.00Z")))
                .value("01:01:01:02")
                .build());
        hlaNomRecords.add(HlaNomRecord.builder()
                .locus(LocusDna.builder()
                        .dna(DNA.C).build()
                )
                .dateAssigned(Date.from(Instant.parse("2005-12-05T10:15:30.00Z")))
                .value("01:05")
                .build());
        hlaNomRecords.add(HlaNomRecord.builder()
                .locus(LocusHLA.builder()
                        .hla(HLA.A).build()
                )
                .dateAssigned(Date.from(Instant.parse("2006-12-05T10:15:30.00Z")))
                .value("1")
                .build());
        hlaNomRecords.add(HlaNomRecord.builder()
                .locus(LocusHLA.builder()
                        .hla(HLA.B).build()
                )
                .dateAssigned(Date.from(Instant.parse("2007-12-05T10:15:30.00Z")))
                .value("2")
                .build());
        when(hlaReaderTest.readFromUrl(progressBar))
                .thenReturn(Optional.of(hlaNomRecords));
    }

    @Test
    void HlaReader_basic_mock() {
        Optional<List<HlaNomRecord>> optionalHlaNomRecords = hlaReaderTest.readFromUrl(progressBar);
        Assertions.assertTrue(optionalHlaNomRecords.isPresent());
        Assertions.assertEquals(hlaNomRecords.get(0), optionalHlaNomRecords.get().get(0));
        Assertions.assertEquals(hlaNomRecords.get(1), optionalHlaNomRecords.get().get(1));
        Assertions.assertEquals(hlaNomRecords.get(2), optionalHlaNomRecords.get().get(2));
        Assertions.assertEquals(hlaNomRecords.get(3), optionalHlaNomRecords.get().get(3));
        Assertions.assertEquals(hlaNomRecords.get(4), optionalHlaNomRecords.get().get(4));
        Assertions.assertEquals(hlaNomRecords.get(5), optionalHlaNomRecords.get().get(5));
    }
}
