package com.example.androidproficiencyexcercise.datasource;

import com.example.androidproficiencyexcercise.model.CanadaRecord;

import java.util.Arrays;
import java.util.List;

public class Stub {
    public static List<CanadaRecord> getRecords(){
        CanadaRecord canadaRecord = new CanadaRecord();
        canadaRecord.setDescription("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony");
        canadaRecord.setTitle("Beavers");
        canadaRecord.setDescription("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");

        List<CanadaRecord> canadaRecords = Arrays.asList(canadaRecord, canadaRecord, canadaRecord, canadaRecord, canadaRecord);
        return canadaRecords;
    }
}
