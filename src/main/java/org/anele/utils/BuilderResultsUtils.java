package org.anele.utils;

import org.anele.pojo.TestRecord;
import org.testng.*;

import java.util.*;

public class BuilderResultsUtils {

    private static List<Map<String, Object>> results;
    public static TestRecord buildRecord(List<ISuite> suites){
        //define TestRecord object to store data
        TestRecord tr = new TestRecord();

        return tr;
    }

    //get the results

    public static List<Map<String, Object>> getResults() {
        return results;
    }
}
