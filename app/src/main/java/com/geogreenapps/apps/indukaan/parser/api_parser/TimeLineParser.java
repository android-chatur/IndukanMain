package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.TimeLine;
import com.geogreenapps.apps.indukaan.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class TimeLineParser extends Parser {

    public TimeLineParser(JSONObject json) {
        super(json);
    }

    public RealmList<TimeLine> getTimeLines() {

        RealmList<TimeLine> list = new RealmList<TimeLine>();
        for (int i = 0; i < json.length(); i++) {
            try {
                JSONObject json_timeLine = json.getJSONObject(i + "");
                TimeLine timeLine = new TimeLine();
                timeLine.setDate(json_timeLine.getString("date"));
                timeLine.setStatus(json_timeLine.getString("status"));
                timeLine.setMessage(json_timeLine.getString("message"));
                timeLine.setSid(json_timeLine.getInt("sid"));

                list.add(timeLine);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return list;
    }


}
