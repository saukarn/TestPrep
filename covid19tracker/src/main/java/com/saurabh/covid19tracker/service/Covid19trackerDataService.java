package com.saurabh.covid19tracker.service;

import com.saurabh.covid19tracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class Covid19trackerDataService {



    private List<LocationStats> allstats = new ArrayList<>();
    public List<LocationStats> getAllstats() {
        return allstats;
    }
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovid19Data() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient Client = HttpClient.newHttpClient();
        String VIRUS_DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URI))
                .build();
        HttpResponse<String> httpResponse = Client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province_State"));
            locationStat.setCounty(record.get("Admin2"));
            int latestcases = Integer.parseInt(record.get(record.size() - 1));
            int previouscases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setConfirmedCases(latestcases);
            locationStat.setDiffFromPrevDay(latestcases - previouscases);

            newStats.add(locationStat);

        }
        this.allstats = newStats;
    }
}
