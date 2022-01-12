package com.geogreenapps.apps.indukaan.parser;


import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.classes.Module;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by Droideve on 1/12/2016.
 */
public class ModuleParser extends Parser {

    public ModuleParser(JSONObject json) {
        super(json);
    }

    public RealmList<Module> getModules() {

        RealmList<Module> list = new RealmList<Module>();
        try {

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i = 0; i < json_array.length(); i++) {

                JSONObject json_modules = json_array.getJSONObject(i + "");
                Module mModule = new Module();

                mModule.setEnabled(json_modules.getInt("enabled"));
                mModule.setName(json_modules.getString("module_name"));

                list.add(mModule);

            }

        } catch (JSONException e) {
            if (AppContext.DEBUG)
                e.printStackTrace();
        }


        return list;
    }


}
