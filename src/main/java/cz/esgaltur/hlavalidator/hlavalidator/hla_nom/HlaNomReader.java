package cz.esgaltur.hlavalidator.hlavalidator.hla_nom;

import javafx.scene.control.ProgressBar;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 *
 */
@Component
@CommonsLog
public class HlaNomReader {
    @Value("https://raw.githubusercontent.com/ANHIG/IMGTHLA/Latest/wmda/hla_nom.txt")
    private String hlaNomUrl;


    public InputStream getStreamFromUrl() throws IOException {
        URL url = new URL(hlaNomUrl);
        return url.openStream();
    }


    public Optional<List<HlaNomRecord>> read(ProgressBar pbProgress, InputStream inputStream) {
        List<HlaNomRecord> hlaNomRecords = new ArrayList<>();
        try {

            try (InputStream stream = inputStream) {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line = br.readLine();
                pbProgress.setProgress(0);
                while (line != null) {
                    if (!line.contains(";")) {
                        line = br.readLine();
                        continue;
                    }
                    String[] attributes = line.split(";");
                    hlaNomRecords.add(new HlaNomRecord(attributes));
                    line = br.readLine();
                    pbProgress.setProgress(pbProgress.getProgress() + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(hlaNomRecords);
    }

    /**
     * @param pbProgress JFXProghressBar
     * @return Optional<List < HlaNomRecord>>
     */
    public Optional<List<HlaNomRecord>> readFromUrl(ProgressBar pbProgress) {
        try {
            return read(pbProgress, getStreamFromUrl());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
