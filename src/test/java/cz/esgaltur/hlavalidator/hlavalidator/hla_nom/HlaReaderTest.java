package cz.esgaltur.hlavalidator.hlavalidator.hla_nom;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ProgressBar;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@CommonsLog
class HlaReaderTest {
    @SpyBean
    HlaNomReader hlaReaderTest;
    ProgressBar progressBar;


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

    @BeforeEach
    public void before() {
        progressBar = new ProgressBar();
        try {
            Mockito.when(hlaReaderTest.getStreamFromUrl())
                    .thenReturn(getClass().getResourceAsStream("/hla_nom.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void HlaReader_basic_mock_is() {
        Optional<List<HlaNomRecord>> optionalHlaNomRecords = hlaReaderTest.readFromUrl(progressBar);
        Assertions.assertTrue(optionalHlaNomRecords.isPresent());
        Assertions.assertTrue(optionalHlaNomRecords.get().size() > 0);
    }
}
